/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_qpidnetwork_request_RequestJniLady */

#ifndef _Included_com_qpidnetwork_request_RequestJniLady
#define _Included_com_qpidnetwork_request_RequestJniLady
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    QueryLadyMatch
 * Signature: (Lcom/qpidnetwork/request/OnQueryLadyMatchCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_QueryLadyMatch
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    SaveLadyMatch
 * Signature: (IIIIILcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_SaveLadyMatch
  (JNIEnv *, jclass, jint, jint, jint, jint, jint, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    QueryLadyList
 * Signature: (IIILjava/lang/String;IIILjava/lang/String;ILjava/lang/String;Lcom/qpidnetwork/request/OnQueryLadyListCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_QueryLadyList
  (JNIEnv *, jclass, jint, jint, jint, jstring, jint, jint, jint, jstring, jint, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    QueryLadyDetail
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnQueryLadyDetailCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_QueryLadyDetail
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    AddFavouritesLady
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_AddFavouritesLady
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    RemoveFavouritesLady
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_RemoveFavouritesLady
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    QueryLadyCall
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnQueryLadyCallCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_QueryLadyCall
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    RecentContact
 * Signature: (Lcom/qpidnetwork/request/OnLadyRecentContactListCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_RecentContact
  (JNIEnv *, jclass, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    RemoveContactList
 * Signature: ([Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_RemoveContactList
  (JNIEnv *, jclass, jobjectArray, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    SignList
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnLadySignListCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_SignList
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniLady
 * Method:    UploadSign
 * Signature: (Ljava/lang/String;[Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniLady_UploadSign
  (JNIEnv *, jclass, jstring, jobjectArray, jobject);

#ifdef __cplusplus
}
#endif
#endif