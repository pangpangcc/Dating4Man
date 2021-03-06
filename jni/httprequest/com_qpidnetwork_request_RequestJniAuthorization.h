/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_qpidnetwork_request_RequestJniAuthorization */

#ifndef _Included_com_qpidnetwork_request_RequestJniAuthorization
#define _Included_com_qpidnetwork_request_RequestJniAuthorization
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    LoginWithFacebook
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnLoginWithFacebookCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_LoginWithFacebook
  (JNIEnv *, jclass, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    Register
 * Signature: (Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnRegisterCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_Register
  (JNIEnv *, jclass, jstring, jstring, jboolean, jstring, jstring, jint, jstring, jstring, jstring, jboolean, jstring, jstring, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    GetCheckCode
 * Signature: (Lcom/qpidnetwork/request/OnRequestOriginalCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_GetCheckCode
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    Login
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnLoginCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_Login
  (JNIEnv *, jclass, jstring, jstring, jstring, jstring, jstring, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    FindPassword
 * Signature: (Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnFindPasswordCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_FindPassword
  (JNIEnv *, jclass, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    GetSms
 * Signature: (Ljava/lang/String;ILjava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_GetSms
  (JNIEnv *, jclass, jstring, jint, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    VerifySms
 * Signature: (Ljava/lang/String;ILcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_VerifySms
  (JNIEnv *, jclass, jstring, jint, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    GetFixedPhone
 * Signature: (Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_GetFixedPhone
  (JNIEnv *, jclass, jstring, jint, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    VerifyFixedPhone
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_VerifyFixedPhone
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    SummitAppToken
 * Signature: (Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_SummitAppToken
  (JNIEnv *, jclass, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniAuthorization
 * Method:    UnbindAppToken
 * Signature: (Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniAuthorization_UnbindAppToken
  (JNIEnv *, jclass, jobject);

#ifdef __cplusplus
}
#endif
#endif
