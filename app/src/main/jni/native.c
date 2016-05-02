#include <jni.h>
#include <stdio.h>
#include "pdp8.h"

extern struct pdp8cpu cpu;

JNIEnv * m_env = NULL;
jclass m_type = NULL;

void doJavaOut(char c) {

    //call the java !
    jmethodID mid = (*m_env)->GetMethodID(m_env, m_type, "teletypeOutput", "(C)V");
    if (mid == 0)
        return;
    printf("In C, about to enter Java\n" );
    (*m_env)->CallVoidMethod(m_env,m_type,mid,c);
    printf("In C, back from Java after the callback\n");
}

char doJavaInput(){
    printf("In C, about to enter Java\n" );
    return -1;
}

JNIEXPORT void JNICALL
Java_com_littlecodeshop_straight8_PDP8_start(JNIEnv *env, jclass type) {
    //demarrer le PDP !
    m_env = env;
    m_type = type;
    registerTeletypeOutput(doJavaOut);
    registerKeyboardInput(doJavaInput);
    startPDP8();

}



JNIEXPORT jstring JNICALL
Java_com_littlecodeshop_straight8_PDP8_status(JNIEnv *env, jclass type) {
    
    char dump[100];
    sprintf(dump,"AC:%04o L:%01o PC:%04o MA:%04o MB:%04o IR:%01o",cpu.ACL&07777,(cpu.ACL&010000),cpu.PC,cpu.MA,cpu.MB,cpu.IR);
    return (*env)->NewStringUTF(env, dump);
}


