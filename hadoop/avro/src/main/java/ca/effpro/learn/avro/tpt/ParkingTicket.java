/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package ca.effpro.learn.avro.tpt;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ParkingTicket extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ParkingTicket\",\"namespace\":\"ca.effpro.learn.avro.tpt\",\"fields\":[{\"name\":\"infractionDate\",\"type\":\"string\"},{\"name\":\"infractionCode\",\"type\":\"int\"},{\"name\":\"fineAmount\",\"type\":\"int\"},{\"name\":\"infractionTime\",\"type\":\"string\"},{\"name\":\"location1\",\"type\":\"string\"},{\"name\":\"location2\",\"type\":\"string\"},{\"name\":\"location3\",\"type\":\"string\"},{\"name\":\"location4\",\"type\":\"string\"},{\"name\":\"province\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence infractionDate;
  @Deprecated public int infractionCode;
  @Deprecated public int fineAmount;
  @Deprecated public java.lang.CharSequence infractionTime;
  @Deprecated public java.lang.CharSequence location1;
  @Deprecated public java.lang.CharSequence location2;
  @Deprecated public java.lang.CharSequence location3;
  @Deprecated public java.lang.CharSequence location4;
  @Deprecated public java.lang.CharSequence province;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public ParkingTicket() {}

  /**
   * All-args constructor.
   */
  public ParkingTicket(java.lang.CharSequence infractionDate, java.lang.Integer infractionCode, java.lang.Integer fineAmount, java.lang.CharSequence infractionTime, java.lang.CharSequence location1, java.lang.CharSequence location2, java.lang.CharSequence location3, java.lang.CharSequence location4, java.lang.CharSequence province) {
    this.infractionDate = infractionDate;
    this.infractionCode = infractionCode;
    this.fineAmount = fineAmount;
    this.infractionTime = infractionTime;
    this.location1 = location1;
    this.location2 = location2;
    this.location3 = location3;
    this.location4 = location4;
    this.province = province;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return infractionDate;
    case 1: return infractionCode;
    case 2: return fineAmount;
    case 3: return infractionTime;
    case 4: return location1;
    case 5: return location2;
    case 6: return location3;
    case 7: return location4;
    case 8: return province;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: infractionDate = (java.lang.CharSequence)value$; break;
    case 1: infractionCode = (java.lang.Integer)value$; break;
    case 2: fineAmount = (java.lang.Integer)value$; break;
    case 3: infractionTime = (java.lang.CharSequence)value$; break;
    case 4: location1 = (java.lang.CharSequence)value$; break;
    case 5: location2 = (java.lang.CharSequence)value$; break;
    case 6: location3 = (java.lang.CharSequence)value$; break;
    case 7: location4 = (java.lang.CharSequence)value$; break;
    case 8: province = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'infractionDate' field.
   */
  public java.lang.CharSequence getInfractionDate() {
    return infractionDate;
  }

  /**
   * Sets the value of the 'infractionDate' field.
   * @param value the value to set.
   */
  public void setInfractionDate(java.lang.CharSequence value) {
    this.infractionDate = value;
  }

  /**
   * Gets the value of the 'infractionCode' field.
   */
  public java.lang.Integer getInfractionCode() {
    return infractionCode;
  }

  /**
   * Sets the value of the 'infractionCode' field.
   * @param value the value to set.
   */
  public void setInfractionCode(java.lang.Integer value) {
    this.infractionCode = value;
  }

  /**
   * Gets the value of the 'fineAmount' field.
   */
  public java.lang.Integer getFineAmount() {
    return fineAmount;
  }

  /**
   * Sets the value of the 'fineAmount' field.
   * @param value the value to set.
   */
  public void setFineAmount(java.lang.Integer value) {
    this.fineAmount = value;
  }

  /**
   * Gets the value of the 'infractionTime' field.
   */
  public java.lang.CharSequence getInfractionTime() {
    return infractionTime;
  }

  /**
   * Sets the value of the 'infractionTime' field.
   * @param value the value to set.
   */
  public void setInfractionTime(java.lang.CharSequence value) {
    this.infractionTime = value;
  }

  /**
   * Gets the value of the 'location1' field.
   */
  public java.lang.CharSequence getLocation1() {
    return location1;
  }

  /**
   * Sets the value of the 'location1' field.
   * @param value the value to set.
   */
  public void setLocation1(java.lang.CharSequence value) {
    this.location1 = value;
  }

  /**
   * Gets the value of the 'location2' field.
   */
  public java.lang.CharSequence getLocation2() {
    return location2;
  }

  /**
   * Sets the value of the 'location2' field.
   * @param value the value to set.
   */
  public void setLocation2(java.lang.CharSequence value) {
    this.location2 = value;
  }

  /**
   * Gets the value of the 'location3' field.
   */
  public java.lang.CharSequence getLocation3() {
    return location3;
  }

  /**
   * Sets the value of the 'location3' field.
   * @param value the value to set.
   */
  public void setLocation3(java.lang.CharSequence value) {
    this.location3 = value;
  }

  /**
   * Gets the value of the 'location4' field.
   */
  public java.lang.CharSequence getLocation4() {
    return location4;
  }

  /**
   * Sets the value of the 'location4' field.
   * @param value the value to set.
   */
  public void setLocation4(java.lang.CharSequence value) {
    this.location4 = value;
  }

  /**
   * Gets the value of the 'province' field.
   */
  public java.lang.CharSequence getProvince() {
    return province;
  }

  /**
   * Sets the value of the 'province' field.
   * @param value the value to set.
   */
  public void setProvince(java.lang.CharSequence value) {
    this.province = value;
  }

  /** Creates a new ParkingTicket RecordBuilder */
  public static ca.effpro.learn.avro.tpt.ParkingTicket.Builder newBuilder() {
    return new ca.effpro.learn.avro.tpt.ParkingTicket.Builder();
  }
  
  /** Creates a new ParkingTicket RecordBuilder by copying an existing Builder */
  public static ca.effpro.learn.avro.tpt.ParkingTicket.Builder newBuilder(ca.effpro.learn.avro.tpt.ParkingTicket.Builder other) {
    return new ca.effpro.learn.avro.tpt.ParkingTicket.Builder(other);
  }
  
  /** Creates a new ParkingTicket RecordBuilder by copying an existing ParkingTicket instance */
  public static ca.effpro.learn.avro.tpt.ParkingTicket.Builder newBuilder(ca.effpro.learn.avro.tpt.ParkingTicket other) {
    return new ca.effpro.learn.avro.tpt.ParkingTicket.Builder(other);
  }
  
  /**
   * RecordBuilder for ParkingTicket instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ParkingTicket>
    implements org.apache.avro.data.RecordBuilder<ParkingTicket> {

    private java.lang.CharSequence infractionDate;
    private int infractionCode;
    private int fineAmount;
    private java.lang.CharSequence infractionTime;
    private java.lang.CharSequence location1;
    private java.lang.CharSequence location2;
    private java.lang.CharSequence location3;
    private java.lang.CharSequence location4;
    private java.lang.CharSequence province;

    /** Creates a new Builder */
    private Builder() {
      super(ca.effpro.learn.avro.tpt.ParkingTicket.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(ca.effpro.learn.avro.tpt.ParkingTicket.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.infractionDate)) {
        this.infractionDate = data().deepCopy(fields()[0].schema(), other.infractionDate);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.infractionCode)) {
        this.infractionCode = data().deepCopy(fields()[1].schema(), other.infractionCode);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.fineAmount)) {
        this.fineAmount = data().deepCopy(fields()[2].schema(), other.fineAmount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.infractionTime)) {
        this.infractionTime = data().deepCopy(fields()[3].schema(), other.infractionTime);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.location1)) {
        this.location1 = data().deepCopy(fields()[4].schema(), other.location1);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.location2)) {
        this.location2 = data().deepCopy(fields()[5].schema(), other.location2);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.location3)) {
        this.location3 = data().deepCopy(fields()[6].schema(), other.location3);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.location4)) {
        this.location4 = data().deepCopy(fields()[7].schema(), other.location4);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.province)) {
        this.province = data().deepCopy(fields()[8].schema(), other.province);
        fieldSetFlags()[8] = true;
      }
    }
    
    /** Creates a Builder by copying an existing ParkingTicket instance */
    private Builder(ca.effpro.learn.avro.tpt.ParkingTicket other) {
            super(ca.effpro.learn.avro.tpt.ParkingTicket.SCHEMA$);
      if (isValidValue(fields()[0], other.infractionDate)) {
        this.infractionDate = data().deepCopy(fields()[0].schema(), other.infractionDate);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.infractionCode)) {
        this.infractionCode = data().deepCopy(fields()[1].schema(), other.infractionCode);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.fineAmount)) {
        this.fineAmount = data().deepCopy(fields()[2].schema(), other.fineAmount);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.infractionTime)) {
        this.infractionTime = data().deepCopy(fields()[3].schema(), other.infractionTime);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.location1)) {
        this.location1 = data().deepCopy(fields()[4].schema(), other.location1);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.location2)) {
        this.location2 = data().deepCopy(fields()[5].schema(), other.location2);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.location3)) {
        this.location3 = data().deepCopy(fields()[6].schema(), other.location3);
        fieldSetFlags()[6] = true;
      }
      if (isValidValue(fields()[7], other.location4)) {
        this.location4 = data().deepCopy(fields()[7].schema(), other.location4);
        fieldSetFlags()[7] = true;
      }
      if (isValidValue(fields()[8], other.province)) {
        this.province = data().deepCopy(fields()[8].schema(), other.province);
        fieldSetFlags()[8] = true;
      }
    }

    /** Gets the value of the 'infractionDate' field */
    public java.lang.CharSequence getInfractionDate() {
      return infractionDate;
    }
    
    /** Sets the value of the 'infractionDate' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setInfractionDate(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.infractionDate = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'infractionDate' field has been set */
    public boolean hasInfractionDate() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'infractionDate' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearInfractionDate() {
      infractionDate = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'infractionCode' field */
    public java.lang.Integer getInfractionCode() {
      return infractionCode;
    }
    
    /** Sets the value of the 'infractionCode' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setInfractionCode(int value) {
      validate(fields()[1], value);
      this.infractionCode = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'infractionCode' field has been set */
    public boolean hasInfractionCode() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'infractionCode' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearInfractionCode() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'fineAmount' field */
    public java.lang.Integer getFineAmount() {
      return fineAmount;
    }
    
    /** Sets the value of the 'fineAmount' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setFineAmount(int value) {
      validate(fields()[2], value);
      this.fineAmount = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'fineAmount' field has been set */
    public boolean hasFineAmount() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'fineAmount' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearFineAmount() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'infractionTime' field */
    public java.lang.CharSequence getInfractionTime() {
      return infractionTime;
    }
    
    /** Sets the value of the 'infractionTime' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setInfractionTime(java.lang.CharSequence value) {
      validate(fields()[3], value);
      this.infractionTime = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'infractionTime' field has been set */
    public boolean hasInfractionTime() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'infractionTime' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearInfractionTime() {
      infractionTime = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /** Gets the value of the 'location1' field */
    public java.lang.CharSequence getLocation1() {
      return location1;
    }
    
    /** Sets the value of the 'location1' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setLocation1(java.lang.CharSequence value) {
      validate(fields()[4], value);
      this.location1 = value;
      fieldSetFlags()[4] = true;
      return this; 
    }
    
    /** Checks whether the 'location1' field has been set */
    public boolean hasLocation1() {
      return fieldSetFlags()[4];
    }
    
    /** Clears the value of the 'location1' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearLocation1() {
      location1 = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /** Gets the value of the 'location2' field */
    public java.lang.CharSequence getLocation2() {
      return location2;
    }
    
    /** Sets the value of the 'location2' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setLocation2(java.lang.CharSequence value) {
      validate(fields()[5], value);
      this.location2 = value;
      fieldSetFlags()[5] = true;
      return this; 
    }
    
    /** Checks whether the 'location2' field has been set */
    public boolean hasLocation2() {
      return fieldSetFlags()[5];
    }
    
    /** Clears the value of the 'location2' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearLocation2() {
      location2 = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /** Gets the value of the 'location3' field */
    public java.lang.CharSequence getLocation3() {
      return location3;
    }
    
    /** Sets the value of the 'location3' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setLocation3(java.lang.CharSequence value) {
      validate(fields()[6], value);
      this.location3 = value;
      fieldSetFlags()[6] = true;
      return this; 
    }
    
    /** Checks whether the 'location3' field has been set */
    public boolean hasLocation3() {
      return fieldSetFlags()[6];
    }
    
    /** Clears the value of the 'location3' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearLocation3() {
      location3 = null;
      fieldSetFlags()[6] = false;
      return this;
    }

    /** Gets the value of the 'location4' field */
    public java.lang.CharSequence getLocation4() {
      return location4;
    }
    
    /** Sets the value of the 'location4' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setLocation4(java.lang.CharSequence value) {
      validate(fields()[7], value);
      this.location4 = value;
      fieldSetFlags()[7] = true;
      return this; 
    }
    
    /** Checks whether the 'location4' field has been set */
    public boolean hasLocation4() {
      return fieldSetFlags()[7];
    }
    
    /** Clears the value of the 'location4' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearLocation4() {
      location4 = null;
      fieldSetFlags()[7] = false;
      return this;
    }

    /** Gets the value of the 'province' field */
    public java.lang.CharSequence getProvince() {
      return province;
    }
    
    /** Sets the value of the 'province' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder setProvince(java.lang.CharSequence value) {
      validate(fields()[8], value);
      this.province = value;
      fieldSetFlags()[8] = true;
      return this; 
    }
    
    /** Checks whether the 'province' field has been set */
    public boolean hasProvince() {
      return fieldSetFlags()[8];
    }
    
    /** Clears the value of the 'province' field */
    public ca.effpro.learn.avro.tpt.ParkingTicket.Builder clearProvince() {
      province = null;
      fieldSetFlags()[8] = false;
      return this;
    }

    @Override
    public ParkingTicket build() {
      try {
        ParkingTicket record = new ParkingTicket();
        record.infractionDate = fieldSetFlags()[0] ? this.infractionDate : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.infractionCode = fieldSetFlags()[1] ? this.infractionCode : (java.lang.Integer) defaultValue(fields()[1]);
        record.fineAmount = fieldSetFlags()[2] ? this.fineAmount : (java.lang.Integer) defaultValue(fields()[2]);
        record.infractionTime = fieldSetFlags()[3] ? this.infractionTime : (java.lang.CharSequence) defaultValue(fields()[3]);
        record.location1 = fieldSetFlags()[4] ? this.location1 : (java.lang.CharSequence) defaultValue(fields()[4]);
        record.location2 = fieldSetFlags()[5] ? this.location2 : (java.lang.CharSequence) defaultValue(fields()[5]);
        record.location3 = fieldSetFlags()[6] ? this.location3 : (java.lang.CharSequence) defaultValue(fields()[6]);
        record.location4 = fieldSetFlags()[7] ? this.location4 : (java.lang.CharSequence) defaultValue(fields()[7]);
        record.province = fieldSetFlags()[8] ? this.province : (java.lang.CharSequence) defaultValue(fields()[8]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
