//**************************** Copyright © Jonathon Bell. All rights reserved.
//*
//*
//*  Version : $Header:$
//*
//*
//*  Purpose :
//*
//*
//*  Comments: This file uses a tab size of 2 spaces.
//*
//*
//****************************************************************************

package com.wolery.ub99

import Utilities._

//****************************************************************************

object Fields
{
// Linear Fields

  def newLinear (n: Name,c: Code,l: ℝ,s: ℝ,h: ℝ)     : Field = newLinear(n,c,l,s,h,l)
  def newLinear (n: Name,c: Code,l: ℝ,s: ℝ,h: ℝ,d: ℝ): Field = newLinear(n,c,d,(0,l),(round((h-l)/s),h))

  def newLevel  (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,  0, 0.1,  10,d)
  def new1To8   (n: Name,c: Code,d: ℝ = 1) = newLinear(n,c,  1, 1,     8,d)
  def new0To10  (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,  0, 1,    10,d)
  def new0To20  (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,  0, 1,    20,d)
  def new0To100 (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,  0, 1,   100,d)
  def newEQGain (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c, -12,0.2, +12,d)
  def newSHGain (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c, -12,0.5, +12,d)
  def newFBGain (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c, -99,1,   +99,d)
  def newFBGain2(n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,-100,2,  +100,d)
  def newIniDlay(n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,   0,0.1, 500,d)
  def newModDlay(n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,   0,0.1, 500,d)
  def newPan    (n: Name,c: Code,d: ℝ = 0) = newLinear(n,c,- 10,0.2, +10,d)
  def newHiRatio(n: Name,c: Code,d: ℝ = 1) = newLinear(n,c,0.1,0.1,  1.0,d)
  def newLoRatio(n: Name,c: Code,d: ℝ = 1) = newLinear(n,c,0.1,0.1,  2.4,d)
  def newModFreq(n: Name,c: Code,d: ℝ = 2) = newLinear(n,c,0.05,0.05, 40,d)
  def newAttGate(n: Name,c: Code,d: ℝ = 4) = newLinear(n,c,0,1,      120,d)

  def newRatio  (n: Name,c: Code,d: ℝ = 1) = newLinear(n,c,d,
      (0x00,  1.0),
      (0x01,  1.1),
    //(0x02,  1.3),
    //(0x03,  1.5),
      (0x04,  1.7),
      (0x05,  2.0),
    //(0x06,  2.5),
    //(0x07,  3.0),
    //(0x08,  3.5),
      (0x09,  4.0),
    //(0x0A,  5.0),
      (0x0B,  6.0),
    //(0x0C,  8.0),
      (0x0D, 10.0),
      (0x0E, 20.0),
      (0x0F,100.0),
      (0x10,Double.MaxValue))

  def newHldGate(n: Name,c: Code,d: ℝ = 150) = newLinear(n,c,d,
      (0,  0.020000),
      (8,  0.382212),
      (16, 0.744425),
      (24, 1.468850),
      (32, 2.917700),
      (40, 5.815400),
      (48, 11.61080),
      (56, 23.20160),
      (64, 46.38320),
      (72, 92.74640),
      (80, 185.4730),
      (88, 370.9250),
      (96, 741.8310),
      (104,1483.640),
      (107,2040.000))

  def newDcyGate(n: Name,c: Code,d: ℝ = 41) = newLinear(n,c,d,
      (0, 6.00000),
      (8, 98.6958),
      (16,191.392),
      (24,376.783),
      (32,747.567),
      (40,1489.13),
      (48,2972.27),
      (56,5938.53),
      (64,11871.1),
      (72,23736.1),
      (79,44.50e3))

  def newDcyTime(n: Name,c: Code,d: ℝ = 70)  = newLinear(n,c,d,
      (0,6),
      (16,15.2621),
      (32,24.5242),
      (48,43.0484),
      (64,80.0968),
      (80,154.194),
      (96,302.387),
      (112,598.774),
      (128,1191.55),
      (144,2377.1),
      (159,46000.0))

  def newRelTime(n: Name,c: Code,d: ℝ = 261) = newLinear(n,c,d,
      (0,6),
      (16,98.6935),
      (32,191.387),
      (48,376.774),
      (64,747.548),
      (80,1489.1),
      (96,2972.19),
      (112,5938.39),
      (127,11500))

  def newRevTime(n: Name,c: Code,d: ℝ = 4.7) = newLinear(n,c,d,
      (0x00,  0.3),
      (0x2F,  5.0),
      (0x39, 10.0),
      (0x43, 20.0),
      (0x53,100.0))

// Frequency Fields

  def newFreqcy (n: Name,c: Code,l: Hz,h: Hz,s: ℕ):       Field = newFreqcy(n,c,l,h,s,l)
//def newFreqcy (n: Name,c: Code,l: Hz,h: Hz,s: ℕ,d: Hz): Field = newFreqcy(n,c,l,h,s,d)

  def newHiPass (n: Name,c: Code,d: Hz = 20.0): Field   = newFreqcy(n,c,20,   8e3, 104,d)
  def newLoPass (n: Name,c: Code,d: Hz = 17e3): Field   = newFreqcy(n,c,50,  17e3, 101,d)
  def newHSHFreq(n: Name,c: Code,d: Hz = 50.0): Field   = newFreqcy(n,c,50,  16e3, 100,d)
  def newLSHFreq(n: Name,c: Code,d: Hz = 21.2): Field   = newFreqcy(n,c,21.2,8e3,  103,d)

// Choice Fields

  def newChoice (n: Name,c: Code,s: String*): Field     = newChoice(n,c,0,s:_*)

  def newPhase  (n: Name,c: Code)                       = newChoice(n,c,0,"Normal","Reverse")
  def newWavSO  (n: Name,c: Code)                       = newChoice(n,c,0,"Sine","Other")
  def newWavTUD (n: Name,c: Code)                       = newChoice(n,c,0,"Triangle","SawUp","SawDown")
  def newPanDir (n: Name,c: Code)                       = newChoice(n,c,0,"L<->R","L->R","L<-R","TurnL","TurnR")
  def newSimType(n: Name,c: Code)                       = newChoice(n,c,0,"Flat","Tube","Solid","R&BVintage","Modern","Classic","Heavy","Drive","Dist","Fuzz")
  def newAmpType(n: Name,c: Code)                       = newChoice(n,c,0,"Heavy1","Heavy2","Lead1","Lead2","Drive1","Drive2","Crunch1","Crunch2","Clean1","Clean2","Solid")
  def newDstType(n: Name,c: Code)                       = newChoice(n,c,0,"Lead1","Lead2","Drive1","Drive2","Crunch1","Crunch2","Fuzz1","Fuzz2","Distortion1","Distortion2","Overdrive1","Overdrive2","Tube","Solid","Bypass")
  def newSpkType(n: Name,c: Code)                       = newChoice(n,c,0,"Off","American412","British412","Modern412","Yamaha412","Hybrid412","American212","British212","Modern212","Yamaha212","Hybrid212","American112","Modern112","Yamaha112","Hybrid112","Generic410","Generic210")
  def newMicType(n: Name,c: Code)                       = newChoice(n,c,0,"Condenser1","Condenser2","Dynamic1","Dynamic2","Tube1","Tube2","Nylon String1","Nylon String2")
  def newDigType(n: Name,c: Code)                       = newChoice(n,c,0,"Distortion1","Distortion2","Overdrive1","Overdrive2","Crunch")
  def newFltType(n: Name,c: Code,d: ℕ)     /**/     = newChoice(n,c,d,"LowPass","HighPass","BandPass")
  def newERType (n: Name,c: Code,d: ℕ)     /**/     = newChoice(n,c,d,"SmallHall","LargeHall","Random","Reverse","Plate","Spring")
  def newWavST  (n: Name,c: Code,d: ℕ = 0) /**/     = newChoice(n,c,d,"Sine","Triangle")

// Field Implementations

  def newName(n: Name,c: Code,d: Name): Field = new FieldOf[String](n,c,d)
  {
    def help                    = println("A string of at most 12 characters")
    def load(b: Bytes)          = m_val = substring(b,16,12)
    def save(b: Bytes)          = m_val.getBytes.copyToArray(b,16)
    override
    def dump(w: Writer)         = w.append('"' + f"$m_val%-12s" + '"')

    override
    def overwrite(s: String)    = s.length<=12 && {m_val=s;true}
  }

  def newKnob(n: Name,c: Code,d: Name): Field = new FieldOf[Name](n,c,d)
  {
    var m_eff: Effect           = null

    override
    def set(e: Effect)          = {assert(m_eff==null && e!=null); m_eff = e}

    def load(b: Bytes)          = get(b) match
    {
      case code if code==none   ⇒ m_val = "NONE"
      case code                 ⇒ m_val = m_eff(code).name
    }

    def save(bytes: Bytes)      = m_val match
    {
      case "NONE"               ⇒ put(bytes,none)
      case  _                   ⇒ put(bytes,m_eff(m_val).code)
    }

    def help                    = tabulate(replace(m_eff.names,s"$default*",default,"KNB1","KNB2","KNB3"))
    override
    def overwrite(v: Name)      =
    {
      (m_eff.m_name.contains(v)||v=="NONE") &&
      {m_val = v;true}
    }
  }

  def newChoice(n: Name,c: Code,d: ℕ,s: String*): Field = new FieldOf[Name](n,c,s(d))
  {
    def help                    = tabulate(replace(s,s"$default*",default))
    def load(b: Bytes)          = {m_val = s(get(b))}
    def save(b: Bytes)          = put(b,s.indexOf(m_val))
    override
    def overwrite(v: Name)      = s.contains(v) && {m_val=v;true}
  }

  def newFreqcy(n: Name,c: Code,l: Hz,h: Hz,s: Hz,d: Hz): Field = new FieldOf[Hz](n,c,d)
  {
    val m_phi: ℝ                = Math.log(h.Hz / l.Hz) / s.Hz
    def help                    = println(s"A frequency between $l and $h [$default*]")
    def load(b: Bytes)          = m_val = l.Hz * Math.exp(m_phi * get(b));
    def save(b: Bytes)          = put(b,round(Math.log(m_val.Hz / l.Hz) / m_phi))

    override
    def overwrite(r: Double)    = inside(r,l.Hz,h.Hz) && {m_val=r;true}
    override
    def increment(v: ℝ)         = overwrite(m_val.Hz+v)
    override
    def decrement(v: ℝ)         = overwrite(m_val.Hz-v)
  }

  def newLinear(n: Name,c: Code,d: ℝ,p: Point*): Field = new FieldOf[ℝ](n,c,d)
  {
    assert(p.size >= 2)

    def help                    = println(s"A number between ${p(0)._2} and ${p.last._2} [$default*]")

    override
    def overwrite(v: ℝ)         = inside(v,p(0)._2,p.last._2) && {m_val=v;true}
    override
    def increment(v: ℝ)         = overwrite(m_val+v)
    override
    def decrement(v: ℝ)         = overwrite(m_val-v)

    def load(b: Bytes) =
    {
      val v = get(b)

      val (i,r,slope) = bind(get(b),_._1)

      m_val = r + (v - i) * slope
    }

    def save(b: Bytes) =
    {
      val (i,r,slope) = bind(m_val,_._2)

      put(b,round(i + (m_val - r)/slope))
    }

    def bind(value: ℝ,f: Point ⇒ ℝ): (ℝ,ℝ,ℝ) =
    {
      var i = 0

      while (outside(value,f(p(i)),f(p(i+1))))
      {
        i += 1
      }

      val (pi,pr) = p(i+0)
      val (qi,qr) = p(i+1)
      (pi,pr,(qr - pr) / (qi - pi))
    }
  }
}

//****************************************************************************
