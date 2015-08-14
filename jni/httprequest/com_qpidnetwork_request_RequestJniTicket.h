/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_qpidnetwork_request_RequestJniTicket */

#ifndef _Included_com_qpidnetwork_request_RequestJniTicket
#define _Included_com_qpidnetwork_request_RequestJniTicket
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_qpidnetwork_request_RequestJniTicket
 * Method:    TicketList
 * Signature: (IILcom/qpidnetwork/request/OnTicketListCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniTicket_TicketList
  (JNIEnv *, jclass, jint, jint, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniTicket
 * Method:    TicketDetail
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnTicketDetailCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniTicket_TicketDetail
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniTicket
 * Method:    ReplyTicket
 * Signature: (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniTicket_ReplyTicket
  (JNIEnv *, jclass, jstring, jstring, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniTicket
 * Method:    ResolvedTicket
 * Signature: (Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniTicket_ResolvedTicket
  (JNIEnv *, jclass, jstring, jobject);

/*
 * Class:     com_qpidnetwork_request_RequestJniTicket
 * Method:    AddTicket
 * Signature: (ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/qpidnetwork/request/OnRequestCallback;)J
 */
JNIEXPORT jlong JNICALL Java_com_qpidnetwork_request_RequestJniTicket_AddTicket
  (JNIEnv *, jclass, jint, jstring, jstring, jstring, jobject);

#ifdef __cplusplus
}
#endif
#endif