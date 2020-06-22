// This C++ program that exercises knowledge of POSIX threads and linked list manipulation.
// Compile with: g++ winterSurvival.cpp

// Header files 
#include	<stdlib.h>
#include	<stdio.h>
#include	<string.h>
#include	<pthread.h>
#include	<unistd.h>
#include	<string>

// Defining constants
// Number of raw materials each group must gather
const int	NUM_MATERIALS_TO_COLLECT = 5;

// Number of village groups that exist
const int	NUM_VILLAGE_GROUPS = 4;

// Hold the names of the raw materials
const char* RAW_MATERIALS_ARRAY[]
				= { "Unharvested crops",
				    "Cattle and chicken",
				    "Lumber and firewood",
				    "Stone",
				    "Oil",
				    "Salt",
				    "Herbs and spices",
				    "Steel"
				  };

// Number of different material names
const size_t	NUM_MATERIAL_NAMES= sizeof(RAW_MATERIALS_ARRAY)/sizeof(const char*);

// Defining the classes and structs

// Represent a RawMaterial
class RawMaterial
{
  // Hold address of the name of the material as a C-string.
  const char* nameCPtr_;

  //  Hold the address of the material instance after '*this' one, or 'NULL' if there is no such material.
  RawMaterial* nextPtr_;

  RawMaterial (const RawMaterial&);

  RawMaterial&	operator=	(const RawMaterial&);

public :
  //  Make '*this' a stand-alone RawMaterial instance with a randomly-chosen name.
  RawMaterial			() :
				nameCPtr_(RAW_MATERIALS_ARRAY[rand() %
					  NUM_MATERIAL_NAMES]
					 ),
				nextPtr_(NULL)
				{ }

  //  Release the resources of '*this'.
  ~RawMaterial			()
				{ }

  // Return the name of the RawMaterial.  No parameters.
  const char*	getNameCPtr	()
				const
				{ return(nameCPtr_); }

  //  To return the address of the RawMaterial instance after '*this' one, or 'NULL' if there is no such RawMaterial.
  RawMaterial*	getNextPtr	()
				const
				{ return(nextPtr_); }

  //  Note that the next RawMaterial in the list has address 'newNextPtr'.
  void		setNextPtr	(RawMaterial* newNextPtr)
				{ nextPtr_ = newNextPtr; }

};

class Material
{
  //  Hold the beginning RawMaterial, or NULL if there is none.
  RawMaterial* beginPtr_;

  //  Hold the ending RawMaterial, or NULL if there is none.
  RawMaterial* endPtr_;

  //  Hold length of '*this' list.
  int numRawMaterials_;

  Material (const Material&);

  Material&	operator=	(const Material&);
 

public :
  //  Initialize '*this' to an empty Material.  No parameters.
  Material () :
				beginPtr_(NULL),
				endPtr_(NULL),
				numRawMaterials_(0)
				{ }

  ~Material ()
				{
				}

  //  Hold length of '*this' list.
  int getNumRawMaterials	()
  				const
				{ return(numRawMaterials_); }

  //  Add the RawMaterial with address 'RawMaterialPtr' at the back of '*this' Material of RawMaterial instances.  
	void store		(RawMaterial* RawMaterialPtr)
				{
				  if  (beginPtr_ == NULL)
				  {
				    beginPtr_	= RawMaterialPtr;
				  }
				  else
				  {
				    endPtr_->setNextPtr(RawMaterialPtr);
				  }

				  endPtr_	= RawMaterialPtr;
				  numRawMaterials_++;
				}

  //  Print this list of RawMaterial instances in '*this' Material
  void	print () {
	const RawMaterial* run;

				  for  (run  = beginPtr_;
				        run != NULL;
					run  = run->getNextPtr()
				       )
				  {
				    printf(" %s\n",run->getNameCPtr());
				  }
				}
 
};

struct Hive
{
  std::string			name_;
  Material*			MaterialPtr_;

  Hive				() :
  				name_(""),
				MaterialPtr_(NULL)
				{ }

  ~Hive				()
  				{
				  delete(MaterialPtr_);
				}

  const char*	getNameCPtr	()
  const { return(name_.c_str()); }

};


// Defining global variables

//  Hold the address of the RawMaterial offered by the farmer or to hold 'NULL' if there is no such RawMaterial.
RawMaterial*		availableRawMaterialPtr	= NULL;

//  Lock access to 'availableRawMaterialPtr'.
pthread_mutex_t	RawMaterialLock;

// Signal when 'availableRawMaterialPtr' points to a RawMaterial.
pthread_cond_t	RawMaterialCond;

// Signal when 'availableRawMaterialPtr' does not point to a RawMaterial.
pthread_cond_t	noRawMaterialCond;

//  Tell how much resources the groups have produced
int	honey = 0;

//  Control access to materials
pthread_mutex_t	honeyLock;


//  To be the function run by the village group threads
void* hive (void* vPtr)
{
  // Initialize local variables
  Hive*		hivePtr		= (Hive*)vPtr;
  Material*	MaterialPtr	= hivePtr->MaterialPtr_;

  //  Each iteration obtains another RawMaterial instance
  while  (MaterialPtr->getNumRawMaterials() < NUM_MATERIALS_TO_COLLECT)
  {
    //  Hang-out outside critical section for a while:
    sleep(rand() % 3);

    //  Lock access to 'availableRawMaterialPtr':
    pthread_mutex_lock(&RawMaterialLock);

    //  Wait for 'availableRawMaterialPtr' to have a RawMaterial:
    while  (availableRawMaterialPtr == NULL)
    {
      printf("%s: \"We need raw materials to make resources!\"\n",hivePtr->getNameCPtr());
      pthread_cond_wait(&RawMaterialCond,&RawMaterialLock);
    }

    //  Get the RawMaterial and store it in Material
    printf("%s:\" %s!  We'll use this to make resources!\"\n",
	   hivePtr->getNameCPtr(),availableRawMaterialPtr->getNameCPtr()
	  );
    MaterialPtr->store(availableRawMaterialPtr);
    availableRawMaterialPtr = NULL;

    //  Unlock access to 'availableRawMaterialPtr':
    pthread_cond_signal(&noRawMaterialCond);
    pthread_mutex_unlock(&RawMaterialLock);
  }

  //  Add to the stockpile when have enough RawMaterials:
  printf("%s \"\nGreat! We have enough resources to make a unit of storable goods!\"\n",
  	 hivePtr->getNameCPtr()
	);
  pthread_mutex_lock(&honeyLock);
  honey++;
  pthread_mutex_unlock(&honeyLock);

  return(NULL);
}


//  Function run by the village elder thread.  'vPtr' is ignored. Returns 'NULL'.
void* farmer (void*	vPtr)
{
  while  (true)
  {
    // Quit when there is sufficient resources:
    pthread_mutex_lock(&honeyLock);

    if  (honey >= NUM_VILLAGE_GROUPS)
    {
      pthread_mutex_unlock(&honeyLock);
      break;
    }

    pthread_mutex_unlock(&honeyLock);

    //  Give another RawMaterial instance:
    printf("\nVillage Elder: \"We have more raw materials to spare! Come get some to make resources!\"\n");

    //  Lock access to 'availableRawMaterialPtr':
    pthread_mutex_lock(&RawMaterialLock);

    //  Wait for the current RawMaterial to be taken:
    while  (availableRawMaterialPtr != NULL)
    {
      printf("\nVillage Elder: \"We have more raw materials to spare! Come get some to make resources!");
      pthread_cond_wait(&noRawMaterialCond,&RawMaterialLock);
    }

    //  Give another RawMaterial:
    availableRawMaterialPtr = new RawMaterial;
    printf("\nVillage Elder: \"We have more raw materials to spare! Come get some to make resources!: a %s\"\n", availableRawMaterialPtr->getNameCPtr());

    //  Unlock access to 'availableRawMaterialPtr':
    pthread_cond_signal(&RawMaterialCond);
    pthread_mutex_unlock(&RawMaterialLock);

    //  Hang-out outside critical section:
    sleep(rand() % 3);
  }

  //  Finished!
  printf("\nVillage Elder:  \"FINALLY! We have enough resources to survive the winter!\"\n");
  return(NULL);
}


//  To run the program.  Ignores command line arguments.  Returns 'EXIT_SUCCESS' to OS.
int	main ()
{
  //  Randomize random number generator
  srand(getpid());

  //  Initialize global variables
  pthread_t	elderThread;
  pthread_t	groupThreadArray[NUM_VILLAGE_GROUPS];
  Hive		  groupArray[NUM_VILLAGE_GROUPS];

  pthread_mutex_init(&RawMaterialLock,NULL);
  pthread_cond_init (&RawMaterialCond,NULL);
  pthread_mutex_init(&honeyLock,NULL);

  // Make group threads:
  for  (int i = 0;  i < NUM_VILLAGE_GROUPS;  i++)
  {
    groupArray[i].name_ = std::string("Group ") + (char)('A'+i);
    groupArray[i].MaterialPtr_	= new Material;
    pthread_create((groupThreadArray+i),NULL,hive,(void*)(groupArray+i));
  }

  //  Make village elder thread
  pthread_create(&elderThread, NULL, farmer, NULL);

  for  (int i = 0;  i < NUM_VILLAGE_GROUPS;  i++)
  {
    pthread_join(groupThreadArray[i], NULL);
    printf("\n %s has secured:\n",groupArray[i].getNameCPtr());
    groupArray[i].MaterialPtr_->print();
  }

  pthread_join(elderThread, NULL);

  //  Get rid of global variables
  pthread_mutex_destroy(&honeyLock);
  pthread_cond_destroy (&RawMaterialCond);
  pthread_mutex_destroy(&RawMaterialLock);

  return(EXIT_SUCCESS);
}
