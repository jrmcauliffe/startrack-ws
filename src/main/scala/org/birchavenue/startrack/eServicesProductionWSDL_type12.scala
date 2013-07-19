// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
package org.birchavenue.startrack


case class ImageContentType(base64Content: Option[String] = None,
  fileFormat: Option[String] = None)


trait ImageTypable {
  val id: Option[String]
  val creationDateTime: Option[javax.xml.datatype.XMLGregorianCalendar]
  val description: Option[String]
  val imageContent: Option[org.birchavenue.startrack.ImageContentType]
}


case class ImageTypeType2(id: Option[String] = None,
  creationDateTime: Option[javax.xml.datatype.XMLGregorianCalendar] = None,
  description: Option[String] = None,
  imageContent: Option[org.birchavenue.startrack.ImageContentType] = None) extends ImageTypable


case class StoreImageType(storeimagetypesequence1: org.birchavenue.startrack.StoreImageTypeSequence1,
  storeimagetypesequence2: org.birchavenue.startrack.StoreImageTypeSequence2,
  storeimagetypesequence3: org.birchavenue.startrack.StoreImageTypeSequence3) {
  lazy val consignmentId = storeimagetypesequence1.consignmentId
  lazy val receiverName1 = storeimagetypesequence1.receiverName1
  lazy val receiverName2 = storeimagetypesequence1.receiverName2
  lazy val receiverAddressLine1 = storeimagetypesequence1.receiverAddressLine1
  lazy val receiverAddressLine2 = storeimagetypesequence1.receiverAddressLine2
  lazy val receiverAddressLine3 = storeimagetypesequence1.receiverAddressLine3
  lazy val receiverAddressSuburb = storeimagetypesequence1.receiverAddressSuburb
  lazy val quantityConsigned = storeimagetypesequence1.quantityConsigned
  lazy val deliveredQuantity = storeimagetypesequence1.deliveredQuantity
  lazy val deliveryDateAndTime = storeimagetypesequence1.deliveryDateAndTime
  lazy val deliveryCode = storeimagetypesequence2.deliveryCode
  lazy val reason = storeimagetypesequence2.reason
  lazy val receiverSignatoryName = storeimagetypesequence2.receiverSignatoryName
  lazy val recieverSignatureCoordinates = storeimagetypesequence2.recieverSignatureCoordinates
  lazy val specialInstructions = storeimagetypesequence2.specialInstructions
  lazy val senderReferences = storeimagetypesequence2.senderReferences
  lazy val signatureRequired = storeimagetypesequence2.signatureRequired
  lazy val noManifestData = storeimagetypesequence2.noManifestData
  lazy val signedOnAttachment = storeimagetypesequence2.signedOnAttachment
  lazy val damaged = storeimagetypesequence2.damaged
  lazy val senderName = storeimagetypesequence3.senderName
  lazy val senderLocation = storeimagetypesequence3.senderLocation
}


case class StoreImageTypeSequence1(consignmentId: String,
  receiverName1: Option[String] = None,
  receiverName2: Option[String] = None,
  receiverAddressLine1: Option[String] = None,
  receiverAddressLine2: Option[String] = None,
  receiverAddressLine3: Option[String] = None,
  receiverAddressSuburb: Option[String] = None,
  quantityConsigned: Int,
  deliveredQuantity: Int,
  deliveryDateAndTime: javax.xml.datatype.XMLGregorianCalendar)

case class StoreImageTypeSequence2(deliveryCode: Option[String] = None,
  reason: Option[String] = None,
  receiverSignatoryName: String,
  recieverSignatureCoordinates: String,
  specialInstructions: Seq[String] = Nil,
  senderReferences: Seq[String] = Nil,
  signatureRequired: Boolean,
  noManifestData: Boolean,
  signedOnAttachment: Boolean,
  damaged: Boolean)

case class StoreImageTypeSequence3(senderName: Option[String] = None,
  senderLocation: Option[String] = None)

