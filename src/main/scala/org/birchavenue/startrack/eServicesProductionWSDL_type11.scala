// Generated by <a href="http://scalaxb.org/">scalaxb</a>.
package org.birchavenue.startrack


case class AccountType(number: Option[BigInt] = None,
  businessUnit: Option[String] = None,
  name: Option[String] = None,
  billingAddress: Option[org.birchavenue.startrack.AddressComplex] = None,
  physicalAddress: Option[org.birchavenue.startrack.AddressComplex] = None,
  certifiedAirFreightIndicator: Option[String] = None,
  riskWarrantyLevel: Option[String] = None,
  serviceCode: Seq[org.birchavenue.startrack.GetServiceCodeType] = Nil,
  unitType: Seq[org.birchavenue.startrack.GetUnitTypeType] = Nil,
  status: Option[String] = None,
  alias: Seq[String] = Nil,
  despatchFreightAllowed: Option[Boolean] = None)


case class CustomerType(id: Option[String] = None,
  name: Option[String] = None,
  dgsAllowed: Option[Boolean] = None,
  lock_seq: Option[String] = None,
  carrier: Seq[org.birchavenue.startrack.CarrierComplex] = Nil,
  contactAddress: Option[org.birchavenue.startrack.AddressComplex] = None)


case class DespatchCustomerType(id: Option[String] = None,
  name: Option[String] = None,
  dgsAllowed: Option[Boolean] = None,
  carrier: Seq[org.birchavenue.startrack.CarrierComplex] = Nil)


case class GetServiceCodeType(code: Option[String] = None,
  lock_seq: Option[String] = None)


case class GetUnitTypeType(unitType: Option[String] = None,
  lock_seq: Option[String] = None)


case class ReceiverTypeType2(name: Seq[String] = Nil,
  despatchLocationCode: Option[String] = None,
  despatchNotification: Option[Boolean] = None,
  id: Option[String] = None,
  shortName: Option[String] = None)

