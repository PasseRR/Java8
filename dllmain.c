/* Replace "dll.h" with the name of your header */
#include "base_Jni.h"
#include <windows.h>
/*
 * Class:     base_Jni
 * Method:    helloWorld
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_base_Jni_helloWorld
  (JNIEnv *env, jobject jo){
  	printf("hello world!\n");
  }

/*
 * Class:     base_Jni
 * Method:    isPrime
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_base_Jni_isPrime
  (JNIEnv *env, jobject jo, jint ji){
  	int flg = 1;
  	int i = 2;
  	for(; i*i <= ji; ++i){
  		if(ji%i == 0){
  			flg = 0;
  			break;
  		}
  	}
  	
  	return (jboolean) flg;
  }

/*
 * Class:     base_Jni
 * Method:    add
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_base_Jni_add
  (JNIEnv *env, jobject jo, jint ji1, jint ji2){
  	int sum = 0;
  	sum = ji1 + ji2;
  	
  	return (jint) sum;
  }

/*
 * Class:     base_Jni
 * Method:    divide
 * Signature: (II)F
 */
JNIEXPORT jfloat JNICALL Java_base_Jni_divide
  (JNIEnv *env, jobject jo, jint ji1, jint ji2){
  	float result = 0;
  	result = ji1*1.0/ji2;
  	
  	return (jfloat) result;
  }

/*
 * Class:     base_Jni
 * Method:    pow
 * Signature: (DI)D
 */
JNIEXPORT jdouble JNICALL Java_base_Jni_pow
  (JNIEnv *env, jobject jo, jdouble jd, jint ji){
  	int i = 0;
  	double result = 1;
  	for(; i < ji; ++i){
  		result *= jd;
  	}
  	
  	return (jdouble) result;
  }

/*
 * Class:     base_Jni
 * Method:    toUpcase
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_base_Jni_toUpcase
  (JNIEnv *env, jobject jo, jstring js){
  	//将Java字符串转换为C字符串 
	char *str = (char *) (*env)->GetStringUTFChars(env, js, 0);
  	int len = strlen(str);
	char temp;
	int i;
	for(i = 0; i < len; ++i){
  		temp = *(str+i);
  		if(temp >= 'a' && temp <= 'z'){
		  temp -= 32;
		  *(str+i) = temp;
  		}
  	}
  	//将C字符串转换为Java字符串 
  	jstring result = (*env)->NewStringUTF(env, str);
  	
  	return result;
  }

/*
 * Class:     base_Jni
 * Method:    split
 * Signature: (Ljava/lang/String;)[Ljava/lang/String;
 */
JNIEXPORT jobjectArray JNICALL Java_base_Jni_split
  (JNIEnv *env, jobject jo, jstring js){
  	//将Java字符串转换为C字符串 
	char *str = (char *) (*env)->GetStringUTFChars(env, js, 0);
	int len = strlen(str);//将要截取的字符串长度 
	int i = 0;
	int j = 0;//结果字符串数组下标 
	int k = 0;//临时字符串下标 
	int length = 0; 
	for(i = 0; i < len; ++i){
		if(' ' == *(str+i)){
			length ++; 
		}
	}
	char *result[length]; 
	char *temp;
	for(i = 0; i < len; ++i){
		if(' ' == *(str+i)){//以空格分割字符串 
			result[j] = temp;
			k = 0;
			j ++;
			temp = "";
		}else if(i == (len-1)){
			result[j] = temp;
			break;
		}else{
			*(temp+k) = *(str+i);
			k ++; 
		}
	}
	jclass objClass = (*env)->FindClass(env, "java/lang/String");   
 	jobjectArray args = (*env)->NewObjectArray(env, length, objClass, 0); //新建数组对象 
 	jstring jstr;
 	//将C字符串数组赋值给Java字符串数组 
 	for (j = 0; j < length; j++) {   
  		jstr = (*env)->NewStringUTF(env, result[j]);   
  		(*env)->SetObjectArrayElement(env, args, j, jstr);   
 	}   
 	return args;  
  }
