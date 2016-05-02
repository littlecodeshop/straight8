
typedef enum {
    AND = 0,
    TAD = 1,
    ISZ = 2,
    DCA = 3,
    JMS = 4,
    JMP = 5,
    IOT = 6,
    OPR = 7
} opCode;

typedef enum {
    FETCH = 0,
    EXEC  = 1,
    DEFER = 2,
    BREAK = 3
} majorState;

struct pdp8cpu{

    unsigned short ACL;
    unsigned short PC;
    unsigned short MA;
    unsigned short MB;
    unsigned short SR;
    unsigned char IR;

    unsigned char HALT;

    unsigned char INTER;

    //current state
    majorState state;
};

int startPDP8();
void initPDP8();
void execOnce();
void registerKeyboardInput(char (*key_in)(void)); //callback into main to check if there is a key
void registerTeletypeOutput(void (*tty_out)(char c));//callback to give char out
