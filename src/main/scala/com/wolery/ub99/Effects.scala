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
//*                                                                     0-0
//*                                                                   (| v |)
//**********************************************************************w*w***

package com.wolery
package ub99

//****************************************************************************

object Effects
{
  import Fields._                                        // Field constructors

  val bykind: Seq[Effect] = Seq(

/* A multi-effect chain designed for electro-acoustic guitars...*/

  Effect(0x00,"Acoustic","BLND","STE","VOL",             // AcousticMulti
   newMicType("TYPE",0x0B),                              // Microphone Type
   newLevel  ("BLND",0x13,10),                           // Mic/Direct Blend
   newEQGain ("BASS",0x14),                              // Bass Gain
   newEQGain ("MID" ,0x15),                              // Middle Gain
   newEQGain ("TRE" ,0x16),                              // Treble Gain
   newEQGain ("PRE" ,0x17),                              // Presence Gain
   newLevel  ("VOL" ,0x18,10),                           // Master Volume
   newLevel  ("STE" ,0x19,10),                           // Stereo Mic Effect
   newFreqcy ("BASF",0x1A,50 ,400  , 127,143),           // Bass Frequency
   newFreqcy ("MIDF",0x1B,200,1.6e3, 127,570),           // Middle Frequency
   newFreqcy ("TREF",0x1C,600,4.8e3, 127,1.71e3),        // Treble Frequency
   newFreqcy ("PREF",0x1D,2e3,16.0e3,127,5.70e3),        // Presence Frequency
   newChoice ("LMSW",0x1E,"Off","On"),                   // Limiter Switch
   newChoice ("ETYP",0x20,"Off","Chorus","Delay"),       // Effect Type
   newChoice ("RTYP",0x22,"Off","Hall","Room","Plate"),  // Reverb Type
   newLevel  ("LIM" ,0x24),                              // Limiter Level
   newLevel  ("SP/T",0x25),                              // Speed/Time
   newLevel  ("D/FB",0x26),                              // Depth/Feedback
   newLevel  ("ELVL",0x27),                              // Effect Level
   newLevel  ("RLVL",0x28)),                             // Reverb Level
 //newKnobs  (0x13,0x19,0x18)),                          // BLND, STE, VOL

/* Eight single-tapped modulated delays connected in parallel...*/

  Effect(0x01,"Delay[11111111]","ELVL","DLVL","DPAN",    // 8BandParaDelay
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.1,0.1,696,295),              // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,1.6),                          // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,3),                            // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT2" ,0x01,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF2",0x1E),                              // Lo Cut Filter
   newLevel  ("HCF2",0x1F),                              // Hi Cut Filter
   newLevel  ("FB2" ,0x20),                              // Feedback Level
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 3, Tap 1
   newLinear ("DT3" ,0x02,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF3",0x29),                              // Lo Cut Filter
   newLevel  ("HCF3",0x2A),                              // Hi Cut Filter
   newLevel  ("FB3" ,0x2B),                              // Feedback Level
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 4, Tap 1
   newLinear ("DT4" ,0x03,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF4",0x34),                              // Lo Cut Filter
   newLevel  ("HCF4",0x35),                              // Hi Cut Filter
   newLevel  ("FB4" ,0x36),                              // Feedback Level
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 5, Tap 1
   newLinear ("DT5" ,0x04,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 6, Tap 1
   newLinear ("DT6" ,0x05,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF6",0x4A),                              // Lo Cut Filter
   newLevel  ("HCF6",0x4B),                              // Hi Cut Filter
   newLevel  ("FB6" ,0x4C),                              // Feedback Level
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 7, Tap 1
   newLinear ("DT7" ,0x06,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF7",0x55),                              // Lo Cut Filter
   newLevel  ("HCF7",0x56),                              // Hi Cut Filter
   newLevel  ("FB7" ,0x57),                              // Feedback Level
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 8, Tap 1
   newLinear ("DT8" ,0x07,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF8",0x60),                              // Lo Cut Filter
   newLevel  ("HCF8",0x61),                              // Hi Cut Filter
   newLevel  ("FB8" ,0x62),                              // Feedback Level
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                            // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* Eight single-tapped modulated delays connected in series...*/

  Effect(0x02,"Delay[Series]","ELVL","DLVL","DPAN",      // 8BandSeriDelay
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.1,0.1,696,368),              // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,2.5),                          // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,3),                            // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT2" ,0x01,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF2",0x1E),                              // Lo Cut Filter
   newLevel  ("HCF2",0x1F),                              // Hi Cut Filter
   newLevel  ("FB2" ,0x20),                              // Feedback Level
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 3, Tap 1
   newLinear ("DT3" ,0x02,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF3",0x29),                              // Lo Cut Filter
   newLevel  ("HCF3",0x2A),                              // Hi Cut Filter
   newLevel  ("FB3" ,0x2B),                              // Feedback Level
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 4, Tap 1
   newLinear ("DT4" ,0x03,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF4",0x34),                              // Lo Cut Filter
   newLevel  ("HCF4",0x35),                              // Hi Cut Filter
   newLevel  ("FB4" ,0x36),                              // Feedback Level
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 5, Tap 1
   newLinear ("DT5" ,0x04,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 6, Tap 1
   newLinear ("DT6" ,0x05,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF6",0x4A),                              // Lo Cut Filter
   newLevel  ("HCF6",0x4B),                              // Hi Cut Filter
   newLevel  ("FB6" ,0x4C),                              // Feedback Level
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 7, Tap 1
   newLinear ("DT7" ,0x06,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF7",0x55),                              // Lo Cut Filter
   newLevel  ("HCF7",0x56),                              // Hi Cut Filter
   newLevel  ("FB7" ,0x57),                              // Feedback Level
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 8, Tap 1
   newLinear ("DT8" ,0x07,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF8",0x60),                              // Lo Cut Filter
   newLevel  ("HCF8",0x61),                              // Hi Cut Filter
   newLevel  ("FB8" ,0x62),                              // Feedback Level
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs(0x08,0x09,0x0A)                              // ELVL, DLVL, DPAN

/* Four twin-tapped modulated delays connected in parallel...*/

  Effect(0x03,"Delay[2222]","ELVL","DLVL","DPAN",        // 4Band2TapModDly
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.1,0.1,1430,1000),            // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,1.7),                          // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,4),                            // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 1, Tap 2
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT3" ,0x02,0.1,0.1,1430),                 // Delay Time
   newLevel  ("LCF3",0x29),                              // Lo Cut Filter
   newLevel  ("HCF3",0x2A),                              // Hi Cut Filter
   newLevel  ("FB3" ,0x2B),                              // Feedback Level
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 2, Tap 2
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 3, Tap 1
   newLinear ("DT5" ,0x04,0.1,0.1,1430),                 // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 3, Tap 2
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 4, Tap 1
   newLinear ("DT7" ,0x06,0.1,0.1,1430),                 // Delay Time
   newLevel  ("LCF7",0x55),                              // Lo Cut Filter
   newLevel  ("HCF7",0x56),                              // Hi Cut Filter
   newLevel  ("FB7" ,0x57),                              // Feedback Level
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 4, Tap 2
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* Two quad-tapped modulated delays connected in parallel...*/

  Effect(0x04,"Delay[44]","ELVL","DLVL","DPAN",          // 2Band4TapM.Dly
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.2,0.2,2920,2000),            // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,2),                            // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,3.2),                          // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 1, Tap 2
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 1, Tap 3
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 1, Tap 4
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT5" ,0x04,0.1,0.2,2920),                 // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 2, Tap 2
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 2, Tap 3
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 2, Tap 4
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* One eight-tapped modulated delay...*/

  Effect(0x05,"Delay[8]","ELVL","DLVL","DPAN",           // 8MultiTapM.Dly
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.5,0.5,5890,5890),            // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15),                              // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,7.3),                          // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 1, Tap 2
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 1, Tap 3
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 1, Tap 4
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 1, Tap 5
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 1, Tap 6
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 1, Tap 7
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 1, Tap 8
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* Two twin-tapped and four single-tapped modulated delays connected in parallel...*/

  Effect(0x06,"Delay[221111]","ELVL","DLVL","DPAN",      // 2Lng4ShrtM.Dly
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.1,0.1,1430,1430),            // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,2.7),                          // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,5.2),                          // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 1, Tap 2
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT3" ,0x02,0.1,0.1,1430),                 // Delay Time
   newLevel  ("LCF3",0x29),                              // Lo Cut Filter
   newLevel  ("HCF3",0x2A),                              // Hi Cut Filter
   newLevel  ("FB3" ,0x2B),                              // Feedback Level
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 2, Tap 2
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 3, Tap 1
   newLinear ("DT5" ,0x04,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 4, Tap 1
   newLinear ("DT6" ,0x05,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF6",0x4A),                              // Lo Cut Filter
   newLevel  ("HCF6",0x4B),                              // Hi Cut Filter
   newLevel  ("FB6" ,0x4C),                              // Feedback Level
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 5, Tap 1
   newLinear ("DT7" ,0x06,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF7",0x55),                              // Lo Cut Filter
   newLevel  ("HCF7",0x56),                              // Hi Cut Filter
   newLevel  ("FB7" ,0x57),                              // Feedback Level
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 6, Tap 1
   newLinear ("DT8" ,0x07,0.1,0.1,696),                  // Delay Time
   newLevel  ("LCF8",0x60),                              // Lo Cut Filter
   newLevel  ("HCF8",0x61),                              // Hi Cut Filter
   newLevel  ("FB8" ,0x62),                              // Feedback Level
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* One single-tapped, one triple-tapped, and one quad-tapped modulated delay
   connected in parallel...*/

  Effect(0x07,"Delay[431]","ELVL","DLVL","DPAN",         // S.M.L.ModDly
   newLevel  ("ELVL",0x08,10),                           // Effect Level
   newLevel  ("DLVL",0x09,10),                           // Direct Level
   newPan    ("DPAN",0x0A),                              // Direct Pan
   newWavTUD ("W.F.",0x0B),                              // Mod Waveform
// Band 1, Tap 1
   newLinear ("DT1" ,0x00,0.1,0.1,696,395.5),            // Delay Time
   newLevel  ("LCF1",0x13),                              // Lo Cut Filter
   newLevel  ("HCF1",0x14),                              // Hi Cut Filter
   newLevel  ("FB1" ,0x15,3.5),                          // Feedback Level
   newWavSO  ("WAV1",0x16),                              // Waveform
   newPhase  ("PHS1",0x17),                              // Phase
   new0To100 ("TAP1",0x18,100),                          // Tap
   newLevel  ("SPD1",0x19),                              // Mod Speed
   newLevel  ("DPT1",0x1A),                              // Mod Depth
   newPan    ("PAN1",0x1B),                              // Pan
   newLevel  ("LVL1",0x1C,4.3),                          // Level
   new1To8   ("SYN1",0x1D,1),                            // Synchronization
// Band 2, Tap 1
   newLinear ("DT2" ,0x01,0.2,0.2,2180),                 // Delay Time
   newLevel  ("LCF2",0x1E),                              // Lo Cut Filter
   newLevel  ("HCF2",0x1F),                              // Hi Cut Filter
   newLevel  ("FB2" ,0x20),                              // Feedback Level
   newWavSO  ("WAV2",0x21),                              // Waveform
   newPhase  ("PHS2",0x22),                              // Phase
   new0To100 ("TAP2",0x23,100),                          // Tap
   newLevel  ("SPD2",0x24),                              // Mod Speed
   newLevel  ("DPT2",0x25),                              // Mod Depth
   newPan    ("PAN2",0x26),                              // Pan
   newLevel  ("LVL2",0x27),                              // Level
   new1To8   ("SYN2",0x28,2),                            // Synchronization
// Band 2, Tap 2
   newWavSO  ("WAV3",0x2C),                              // Waveform
   newPhase  ("PHS3",0x2D),                              // Phase
   new0To100 ("TAP3",0x2E,100),                          // Tap
   newLevel  ("SPD3",0x2F),                              // Mod Speed
   newLevel  ("DPT3",0x30),                              // Mod Depth
   newPan    ("PAN3",0x31),                              // Pan
   newLevel  ("LVL3",0x32),                              // Level
   new1To8   ("SYN3",0x33,3),                            // Synchronization
// Band 2, Tap 3
   newWavSO  ("WAV4",0x37),                              // Waveform
   newPhase  ("PHS4",0x38),                              // Phase
   new0To100 ("TAP4",0x39,100),                          // Tap
   newLevel  ("SPD4",0x3A),                              // Mod Speed
   newLevel  ("DPT4",0x3B),                              // Mod Depth
   newPan    ("PAN4",0x3C),                              // Pan
   newLevel  ("LVL4",0x3D),                              // Level
   new1To8   ("SYN4",0x3E,4),                            // Synchronization
// Band 3, Tap 1
   newLinear ("DT5" ,0x04,0.2,0.2,2920),                 // Delay Time
   newLevel  ("LCF5",0x3F),                              // Lo Cut Filter
   newLevel  ("HCF5",0x40),                              // Hi Cut Filter
   newLevel  ("FB5" ,0x41),                              // Feedback Level
   newWavSO  ("WAV5",0x42),                              // Waveform
   newPhase  ("PHS5",0x43),                              // Phase
   new0To100 ("TAP5",0x44,100),                          // Tap
   newLevel  ("SPD5",0x45),                              // Mod Speed
   newLevel  ("DPT5",0x46),                              // Mod Depth
   newPan    ("PAN5",0x47),                              // Pan
   newLevel  ("LVL5",0x48),                              // Level
   new1To8   ("SYN5",0x49,5),                            // Synchronization
// Band 3, Tap 2
   newWavSO  ("WAV6",0x4D),                              // Waveform
   newPhase  ("PHS6",0x4E),                              // Phase
   new0To100 ("TAP6",0x4F,100),                          // Tap
   newLevel  ("SPD6",0x50),                              // Mod Speed
   newLevel  ("DPT6",0x51),                              // Mod Depth
   newPan    ("PAN6",0x52),                              // Pan
   newLevel  ("LVL6",0x53),                              // Level
   new1To8   ("SYN6",0x54,6),                            // Synchronization
// Band 3, Tap 3
   newWavSO  ("WAV7",0x58),                              // Waveform
   newPhase  ("PHS7",0x59),                              // Phase
   new0To100 ("TAP7",0x5A,100),                          // Tap
   newLevel  ("SPD7",0x5B),                              // Mod Speed
   newLevel  ("DPT7",0x5C),                              // Mod Depth
   newPan    ("PAN7",0x5D),                              // Pan
   newLevel  ("LVL7",0x5E),                              // Level
   new1To8   ("SYN7",0x5F,7),                            // Synchronization
// Band 3, Tap 4
   newWavSO  ("WAV8",0x63),                              // Waveform
   newPhase  ("PHS8",0x64),                              // Phase
   new0To100 ("TAP8",0x65,100),                          // Tap
   newLevel  ("SPD8",0x66),                              // Mod Speed
   newLevel  ("DPT8",0x67),                              // Mod Depth
   newPan    ("PAN8",0x68),                              // Pan
   newLevel  ("LVL8",0x69),                              // Level
   new1To8   ("SYN8",0x6A,8)),                           // Synchronization
 //newKnobs  (0x08,0x09,0x0A)),                          // ELVL, DLVL, DPAN

/* Guitar amplifier simulation modeled on the Yamaha DG series amplifiers...*/

  Effect(0x08,"Amp","GAIN","MSTR","TONE",                // AmpSimulator
   newAmpType("AMP" ,0x0B),                              // Amplifier Type
   newSpkType("SP"  ,0x0C),                              // Speaker Type
   newLevel  ("GAIN",0x13,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x14,5.5),                          // Master Volume
   newLevel  ("TONE",0x17,10),                           // Master Tone
   newLevel  ("TRE" ,0x19,5),                            // Treble
   newLevel  ("HMID",0x1A,5),                            // Hi Mid
   newLevel  ("LMID",0x1B,5),                            // Lo Mid
   newLevel  ("BASS",0x1C,5),                            // Bass
   newLevel  ("PRE" ,0x1D,5),                            // Presence
   newLevel  ("NGTH",0x1F,0),                            // Gate Threshold
   newAttGate("NGAT",0x20,4),                            // Gate Attack
   newHldGate("NGHL",0x21),                              // Gate Hold Level
   newDcyGate("NGDC",0x22)),                             // Gate Decay Time
 //newKnobs  (0x13,0x14,0x17)),                          // GAIN, MASTR, TONE

/* Hall, room, stage, and plate reverb simulations, all with gates...*/

  Effect(0x09,"Reverb","TIME","HRAT","MIX",              // Reverb
   newHiRatio("HRAT",0x00,0.3),                          // Hi Freq Ratio
   newIniDlay("IDLY",0x08,36),                           // Initial Delay
   newLinear ("ERDL",0x09,0,0.1,100,2),                  // ER/Rev Delay
   newRevTime("TIME",0x0A,3.2),                          // Reverb Time
   newChoice ("TYPE",0x0B,"Hall","Room","Stage","Plate"),// Reverb Type
   newLoRatio("LRAT",0x13,1.4),                          // Lo Freq Ratio
   new0To10  ("DIFF",0x14,8),                            // Stereo Diffusion
   new0To100 ("DNST",0x15,100),                          // Reflection Density
   new0To100 ("ERBL",0x16,44),                           // ER/Rev Balance
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x18,6.7e3),                        // Lo Pass Frequency
   newLinear ("GATE",0x19,-61,1,0),                      // Gate Level
   newAttGate("ATCK",0x1A,4),                            // Gate Attack
   newHldGate("HOLD",0x1B),                              // Gate Hold Level
   newDcyGate("DCAY",0x1C),                              // Gate Decay Time
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x0A,0x00,0x1D)),                          // TIME, HRAT, MIX

/* Early reverberation reflections simulation...*/

  Effect(0x0A,"Reflections","LIVE","HRAT","MIX",         // EarlyRef.
   newIniDlay("IDLY",0x08,3),                            // Initial Delay
   newFBGain ("FB"  ,0x09,2),                            // Feedback Gain
   newLinear ("SIZE",0x0A,0.1,0.1,20,2.4),               // Room Size
   newERType ("TYPE",0x0B,1),                            // Room Type
   new0To10  ("LIVE",0x14,7),                            // Room Liveness
   new0To10  ("DIFF",0x15,7),                            // Stereo Diffusion
   new0To100 ("DNST",0x16,85),                           // Reflection Density
   newLinear ("ERNO",0x17,1,1,19,11),                    // Reflection Count
   newHiRatio("HRAT",0x1A,0.4),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x1B),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x1C,11.8e3),                       // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x14,0x1A,0x1D)),                          // LIVE, HRAT, MIX

/* Early reverberation reflections simulation with gate...*/

  Effect(0x0B,"Reverb[Gated]","LIVE","HRAT","MIX",       // GateReverb
   newIniDlay("IDLY",0x08,3),                            // Initial Delay
   newFBGain ("FB"  ,0x09,2),                            // Feedback Gain
   newLinear ("SIZE",0x0A,0.1,0.1,20,2.4),               // Room Size
   newChoice ("TYPE",0x0B,"A","B"),                      // Room Type
   new0To10  ("LIVE",0x14,7),                            // Room Liveness
   new0To10  ("DIFF",0x15,7),                            // Stereo Diffusion
   new0To100 ("DNST",0x16,85),                           // Reflection Density
   newLinear ("ERNO",0x17,1,1,19,11),                    // Reflection Count
   newHiRatio("HRAT",0x1A,0.4),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x1B),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x1C,11.8e3),                       // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x14,0x1A,0x1D)),                          // LIVE, HRAT, MIX

/* Early reflections simulation with reverse gate...*/

  Effect(0x0C,"ReverseGate","LIVE","HRAT","MIX",         // ReverseGate
   newIniDlay("IDLY",0x08,3),                            // Initial Delay
   newFBGain ("FB"  ,0x09,2),                            // Feedback Gain
   newLinear ("SIZE",0x0A,0.1,0.1,20,2.4),               // Room Size
   newChoice ("TYPE",0x0B,"A","B"),                      // Room Type
   new0To10  ("LIVE",0x14,7),                            // Room Liveness
   new0To10  ("DIFF",0x15,7),                            // Stereo Diffusion
   new0To100 ("DNST",0x16,85),                           // Reflection Density
   newLinear ("ERNO",0x17,1,1,19,11),                    // Reflection Count
   newHiRatio("HRAT",0x1A,0.4),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x1B),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x1C,11.8e3),                       // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x14,0x1A,0x1D)),                          // LIVE, HRAT, MIX

/* Monophonic delay effect...*/

  Effect(0x0D,"Delay[Mono]","TIME","FB","MIX",           // MonoDelay
   newLinear ("TIME",0x08,0,0.2,2730,250),               // Delay Time
   newFBGain ("FB"  ,0x09,40),                           // Feedback Gain
   newHiRatio("HRAT",0x14,0.6),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x15),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x16,10e3),                         // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x1D)),                          // TIME, FB, MIX

/* Stereophonic delay effect...*/

  Effect(0x0E,"Delay[Stereo]","DT.L","DT.R","MIX",       // StereioDelay
   newFBGain ("FB.R",0x00,25),                           // Feedback Gain [R]
   newLinear ("DT.L",0x08,0,0.1,1350,250),               // Delay Time    [L]
   newLinear ("DT.R",0x09,0,0.1,1350,373),               // Delay Time    [R]
   newFBGain ("FB.L",0x0A,40),                           // Feedback Gain [L]
   newHiRatio("HRAT",0x13,0.5),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15,10e3),                         // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x1D)),                          // DT.L, DT.R, MIX

/* Monophonic delay effect with delay time modulation...*/

  Effect(0x0F,"Delay[Modulated]","TIME","FB","MIX",      // Mod.Delay
   newLinear ("TIME",0x08,0,0.2,2725,250),               // Delay Time
   newFBGain ("FB"  ,0x09,38),                           // Feedback Gain
   newModFreq("FREQ",0x0A,0.85),                         // Mod Frequency
   newWavST  ("WAVE",0x0B),                              // Mod Waveform
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15,12.5e3),                       // Lo Pass Frequency
   newHiRatio("HRAT",0x16,0.4),                          // Hi Freq Ratio
   new0To100 ("DPT" ,0x17,60),                           // Mod Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x1D)),                          // TIME, FB, MIX

/* Triple-tapped delay, panned left, center, and right...*/

  Effect(0x10,"Delay[LCR]","DTFB","FB","MIX",            // DelayLCR
   newLinear ("DTFB",0x00,0,0.2,2730,750),               // Feedback Delay Time
   newLinear ("DT.L",0x08,0,0.2,2730,250),               // Delay Time  [L]
   newLinear ("DT.C",0x09,0,0.2,2730,750),               // Delay Time  [C]
   newLinear ("DT.R",0x0A,0,0.2,2730,500),               // Delay Time  [R]
   newLinear ("LV,L",0x13,-100,2,+100,80),               // Delay Level [L]
   newLinear ("LV,C",0x14,-100,2,+100,80),               // Delay Level [C]
   newLinear ("LV,R",0x15,-100,2,+100,80),               // Delay Level [R]
   newFBGain2("FB"  ,0x16,40),                           // Feedback Gain
   newHiRatio("HRAT",0x17,0.6),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x18),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19),                              // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x00,0x16,0x1D)),                          // DTFB, FB, MIX

/* Stereophonic delay effect with a crossed feedback loop...*/

  Effect(0x11,"Echo","DTFL","DTFR","MIX",                // Echo
   newLinear ("DT.L",0x08,0,0.1,1350,176),               // Delay Time    [L]
   newLinear ("DT.R",0x09,0,0.1,1350,246),               // Delay Time    [R]
   newLinear ("DTFL",0x0A,0,0.1,1350,246),               // Delay Time FB [L]
   newLinear ("DTFR",0x00,0,0.1,1350,176),               // Delay Time FB [R]
   newFBGain2("FB.L",0x13,+50),                          // Feedback Gain [L]
   newFBGain2("FB.R",0x14,-50),                          // Feedback Gain [R]
   newFBGain2("L->R",0x15,+36),                          // Feedback Gain [L>R]
   newFBGain2("R->L",0x16,+36),                          // Feedback Gain [R>L]
   newHiRatio("HRAT",0x17,0.4),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x18),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,6e3),                          // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x0A,0x00,0x1D)),                          // FTFL, FTFR, MIX

/* Chorus effect...*/

  Effect(0x12,"Chorus","FREQ","PMDP","MIX",              // Chorus
   newModDlay("MDT" ,0x00,12),                           // Mod Delay
   newModFreq("FREQ",0x08,0.75),                         // Mod Frequency
   new0To100 ("AMDP",0x09,28),                           // AM Depth
   new0To100 ("PMDP",0x0A,28),                           // PM Depth
   newWavST  ("WAVE",0x0B),                              // Mod Waveform
   newLSHFreq("LSHF",0x13),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x14),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x15,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x16),                              // EQ Gain
   newFreqcy ("EQ.Q",0x17,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x18),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x19),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x0A,0x1D)),                          // FREQ, PMDP, MIX

/* Flanger effect...*/

  Effect(0x13,"Flanger","FREQ","DPT","FB",               // Flanger
   newModDlay("MDT" ,0x00,2.4),                          // Mod Delay
   newModFreq("FREQ",0x08,0.25),                         // Mod Frequency
   new0To100 ("DPT" ,0x09,60),                           // Mod Depth
   newFBGain ("FB"  ,0x0A,-83),                          // Feedback Gain
   newWavST  ("WAVE",0x0B),                              // Mod Waveform
   newLSHFreq("LSHF",0x13),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x14),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x15,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x16),                              // EQ Gain
   newFreqcy ("EQ.Q",0x17,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x18),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x19),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x0A)),                          // FREQ, DPT, FB

/* Symphonic effect...*/

  Effect(0x14,"Symphonic","FREQ","DPT","MIX",            // Symphonic
   newModFreq("FREQ",0x08,1.6),                          // Mod Frequency
   new0To100 ("DPT" ,0x09,50),                           // Mod Depth
   newModDlay("MDT" ,0x0A,3),                            // Mod Delay
   newWavST  ("WAVE",0x0B),                              // Mod Waveform
   newLSHFreq("LSHF",0x13),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x14),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x15,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x16),                              // EQ Gain
   newFreqcy ("EQ.Q",0x17,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x18),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x1A),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x1D)),                          // FRQ, DPT, MIX

/* Phase shifter effect...*/

  Effect(0x15,"Phaser","FREQ","DPT","FB",                // Phaser
   new0To100 ("OFST",0x00,46),                           // Lowest Offset
   newModFreq("FREQ",0x08,0.35),                         // Mod Frequency
   new0To100 ("DPT" ,0x09,28),                           // Mod Depth
   newFBGain ("FB"  ,0x0A,78),                           // Feedback Gain
   newLinear ("STAG",0x0B,2,2,16,8),                     // Shifter Stages
   newLinear ("PHAS",0x13,0,5.63,354.38),                // L/R Phase Angle
   newLSHFreq("LSHF",0x14),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x15),                              // Lo Shelf Gain
   newHSHFreq("HSHF",0x16),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x17),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x08,0x09,0x0A)),                          // FRQ, DPT, FB

/* Automatic stereo panning effect...*/

  Effect(0x16,"Pan","WAVE","FREQ","DPT",                 // AutoPan
   newModFreq("FREQ",0x00,1.55),                         // Mod Frequency
   newChoice ("WAVE",0x0B,0,"Sine","Triangle","Square"), // Mod Waveform
   new0To100 ("DPT" ,0x13,100),                          // Mod Depth
   newPanDir ("DIR" ,0x14),                              // Panning direction
   newLSHFreq("LSHF",0x15),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x16),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x18,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x19),                              // EQ Gain
   newFreqcy ("EQ.Q",0x1A,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x1B),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x1C)),                             // Hi Shelf Gain
 //newKnobs  (0x0B,0x00,0x13)),                          // WAVE, FREQ, DPT

/* Tremolo effect...*/

  Effect(0x17,"Tremolo","WAVE","FREQ","DPT",             // Tremolo
   newModFreq("FREQ",0x00,4.05),                         // Mod Frequency
   newChoice ("WAVE",0x0B,1,"Sine","Triangle","Square"), // Mod Waveform
   new0To100 ("DPT" ,0x13,91),                           // Mod Depth
   newLSHFreq("LSHF",0x14),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x15),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x18,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x19),                              // EQ Gain
   newFreqcy ("EQ.Q",0x1A,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x1B),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x1C)),                             // Hi Shelf Gain
 //newKnobs  (0x0B,0x00,0x13)),                          // WAVE, FREQ, DPT

/* Pitch shifter effect...*/

  Effect(0x18,"Pitch[1]","PIT","FINE","MIX",             // H.Q.Pitch
   newLinear ("DT"  ,0x08,0,0.1,1000),                   // Delay Time
   newFBGain ("FB"  ,0x09,0),                            // Feedback Gain
   newLinear ("MODE",0x0B,1,1,10,7),                     // Precision
   newLinear ("PIT" ,0x13,-12,1,+12,-5),                 // Pitch Coarse
   newLinear ("FINE",0x14,-50,1,+50,0),                  // Pitch Fine
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x14,0x1D)),                          // PIT, FINE, MIX

/* Dual pitch shifter effect...*/

  Effect(0x19,"Pitch[2]","PIT1","PIT2","MIX",            // DualPitch
   newFBGain ("FB2" ,0x00,0),                            // Feedback Gain [2]
   newLinear ("DT1" ,0x08,0,0.1,1000,60),                // Delay Time    [1]
   newFBGain ("FB1" ,0x09,0),                            // Feedback Gain [1]
   newLinear ("DT2" ,0x0A,0,0.1,1000,30),                // Delay Time    [2]
   newLinear ("MODE",0x0B,1,1,10,6),                     // Precision
   newLinear ("PIT1",0x13,-24,1,+24,-12),                // Pitch Coarse  [1]
   newLinear ("FIN1",0x14,-50,1,+50,-13),                // Pitch Fine    [1]
   newLinear ("LVL1",0x15,-100,2,+100,100),              // Level         [1]
   newLinear ("PAN1",0x16,-63,1,+63,+63),                // Pan           [1]
   newLinear ("PIT2",0x17,-24,1,+24,+7),                 // Pitch Coarse  [2]
   newLinear ("FIN2",0x18,-50,1,+50,-3),                 // Pitch Fine    [2]
   newLinear ("LVL2",0x19,-100,2,+100,60),               // Level         [2]
   newLinear ("PAN2",0x1A,-63,1,+63),                    // Pan           [2]
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x17,0x1D)),                          // PIT1, PIT2, MIX

/* Rotary speaker simulation...*/

  Effect(0x1A,"Rotary","DRV","ROT","SPD",                // Rotary
   newLinear ("SLOW",0x08,0.05,0.05,10,0.65),            // Slow Speed
   newLinear ("FAST",0x09,0.05,0.05,10,2.10),            // Fast Speed
   newChoice ("ROT" ,0x0B,1,"Stop","Start"),             // Rotation Control
   newChoice ("SPD" ,0x13,1,"Slow","Fast"),              // Rotation Speed
   new0To100 ("DRV" ,0x14,80),                           // Overdrive Level
   new0To10  ("ACCL",0x15,5),                            // Acceleration
   new0To100 ("LOW" ,0x16,100),                          // Lo Freq Filter
   new0To100 ("HIGH",0x17,95),                           // Hi Freq Filter
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x14,0x0B,0x13)),                          // DRV, ROT, SPD

/* Ring modulator effect...*/

  Effect(0x1B,"RingModulator","SRC","FM.F","MIX",        // RingMod.
   newLinear ("OSC" ,0x08,0,0.5,5000,905.5),             // Oscillator Frequency
   newModFreq("FM.F",0x09,0.2),                          // FM Frequency
   newChoice ("SRC" ,0x0B,"Osc","Self"),                 // Modulation Source
   new0To100 ("FM.D",0x16,60),                           // FM Depth
   new0To100 ("MIX" ,0x1D,100)),                         // Mix
 //newKnobs  (0x08,0x09,0x1D)),                          // DT, FB, LEVL

/* Modulated filter effect...*/

  Effect(0x1C,"Filter[Modulated]","FREQ","DPTH","RESO",  // Mod.Filter
   newModFreq("FREQ",0x00,1.25),                         // Mod Frequency
   newFltType("TYPE",0x0B,2),                            // Filter Type
   new0To100 ("DPTH",0x13,60),                           // Mod Depth
   newLinear ("PHAS",0x14,0,5.63,354.38),                // L/R Phase Angle
   new0To100 ("OFST",0x15,10),                           // Lowest Offset
   newLinear ("RESO",0x16,0,1,20,10),                    // Filter Resonance
   new0To100 ("LEVL",0x17,70),                           // Output Level
   new0To100 ("MIX" ,0x1D,100)),                         // Mix
 //newKnobs  (0x00,0x13,0x16)),                          // FREQ, DPTH, RESO

/* Digitial distortion effect...*/

  Effect(0x1D,"Distortion[Digital]","DRV","MSTR","TONE", // DigiDistortion
   newDigType("TYPE",0x0B),                              // Distortion Type
   new0To100 ("DRV" ,0x13,90),                           // Distortion Drive
   new0To100 ("MSTR",0x14,40),                           // Master Volume
   newLinear ("TONE",0x15,-10,1,10,-5),                  // Master Tone
   newLinear ("NG"  ,0x16,0,1,20,2)),                    // Noise Gate
 //newKnobs(0x13,0x14,0x15)                              // DRV, MSTR, TONE

/* Dynamic filter effect...*/

  Effect(0x1E,"Filter[Dynamic]","SENS","OFST","DCY",     // Dyna.Filter
   newDcyTime("DCY" ,0x0A,70),                           // Decay Time
   newFltType("TYPE",0x0B,2),                            // Filter Type
   newChoice ("DIR" ,0x13,"Up","Down"),                  // Direction
   new0To100 ("SENS",0x14,60),                           // Sensitivity
   new0To100 ("OFST",0x15,9),                            // Lowest Offset
   newLinear ("RESO",0x16,0,1,20,13),                    // Filter Resonance
   new0To100 ("LEVL",0x17,90),                           // Output Level
   new0To100 ("MIX" ,0x1D,100)),                         // Mix
 //newKnobs  (0x14,0x15,0x0A)),                          // SENS, OFST, DCY

/* Dynamic flanger effect...*/

  Effect(0x1F,"Flanger[Dynamic]","SENS","FB","DCY",      // Dyna.Flanger
   newFBGain ("FB"  ,0x00,-88),                          // Feedback Gain
   newDcyTime("DCY" ,0x0A,23),                           // Decay Time
   newChoice ("DIR" ,0x13,"Up","Down"),                  // Direction
   new0To100 ("SENS",0x14,59),                           // Sensitivity
   new0To100 ("OFST",0x15,67),                           // Lowest Offset
   newLSHFreq("LSHF",0x16),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x17),                              // Lo Shelf Gain
   newFreqcy ("EQ.F",0x18,100,8e3,76),                   // EQ Frequency
   newSHGain ("EQ.G",0x19),                              // EQ Gain
   newFreqcy ("EQ.Q",0x1A,10,0.10,40),                   // EQ Bandwidth
   newHSHFreq("HSHF",0x1B),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x1C),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x14,0x00,0x0A)),                          // SENS, FB, DCY

/* Dynamic phaser effect...*/

  Effect(0x20,"Phaser[Dynamic]","SENS","FB","DCY",       // Dyna.Phaser
   newFBGain ("FB"  ,0x00,-88),                          // Feedback Gain
   newDcyTime("DCY" ,0x0A,470),                          // Decay Time
   newChoice ("DIR" ,0x13,"Up","Down"),                  // Direction
   new0To100 ("SENS",0x14,62),                           // Sensitivity
   new0To100 ("OFST",0x15,29),                           // Lowest Offset
   newLinear ("STAG",0x16,2,2,16,10),                    // Shifter Stages
   newLSHFreq("LSHF",0x18),                              // Lo Shelf Frequency
   newSHGain ("LSHG",0x19),                              // Lo Shelf Gain
   newHSHFreq("HSHF",0x1A),                              // Hi Shelf Frequency
   newSHGain ("HSHG",0x1B),                              // Hi Shelf Gain
   new0To100 ("MIX" ,0x1D,80)),                          // Mix
 //newKnobs  (0x14,0x00,0x0A)),                          // SENS, FB, DCY

/* Reverb and chorus effects in parralel...*/

  Effect(0x21,"Reverb+Chorus","TIME","BAL","MIX",        // Reverb+Chorus
   newIniDlay("IDLY",0x08,30),                           // Initial Delay
   newModFreq("FREQ",0x09,0.55),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,8.8),                          // Mod Delay
   newWavST  ("WAVE",0x0B,0),                            // Mod Waveform
   newRevTime("RT"  ,0x13,2.2),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.3),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,90),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,9.5e3),                        // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,30),                           // Reverb Balance
   new0To100 ("AMDP",0x1B,60),                           // AM Depth
   new0To100 ("PMDP",0x1C,60),                           // PM Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // TIME, BAL, MIX

/* Reverb and chorus effects in series...*/

  Effect(0x22,"Reverb>Chorus","TIME","BAL","MIX",        // Reverb->Chorus
   newIniDlay("IDLY",0x08,22),                           // Initial Delay
   newModFreq("FREQ",0x09,1.95),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,7),                            // Mod Delay
   newWavST  ("WAVE",0x0B,1),                            // Mod Waveform
   newRevTime("RT"  ,0x13,1.8),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.3),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,77),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,5.6e3),                        // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,30),                           // Reverb Balance
   new0To100 ("AMDP",0x1B,43),                           // AM Depth
   new0To100 ("PMDP",0x1C,43),                           // PM Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // TIME, BAL, MIX

/* Reverb and flanger effects in parallel...*/

  Effect(0x23,"Reverb+Flanger","RT","BAL","MIX",         // Reverb+Flange
   newFBGain ("FB"  ,0x00,82),                           // Feedback Gain
   newIniDlay("IDLY",0x08,28),                           // Initial Delay
   newModFreq("FREQ",0x09,0.75),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,2.2),                          // Mod Delay
   newWavST  ("WAVE",0x0B,1),                            // Mod Waveform
   newRevTime("RT"  ,0x13,1.6),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.3),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,95),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,9.5e3),                        // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,24),                           // Reverb Balance
   new0To100 ("DPTH",0x1B,61),                           // Mod Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // RT, BAL, MIX

/* Reverb and flanger effects in series...*/

  Effect(0x24,"Reverb>Flanger","RT","BAL","MIX",         // Reverb->Flange
   newFBGain ("FB"  ,0x00,43),                           // Feedback Gain
   newIniDlay("IDLY",0x08,15.1),                         // Initial Delay
   newModFreq("FREQ",0x09,1.95),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,2.0),                          // Mod Delay
   newWavST  ("WAVE",0x0B,0),                            // Mod Waveform
   newRevTime("RT"  ,0x13,2.7),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.5),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,80),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,3.15e3),                       // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,16),                           // Reverb Balance
   new0To100 ("DPTH",0x1B,36),                           // Mod Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // RT, BAL, MIX

/* Reverb and symphonic effects in parallel...*/

  Effect(0x25,"Reverb+Symphonic","RT","BAL","MIX",       // Rever+Symphonic
   newIniDlay("IDLY",0x08,19),                           // Initial Delay
   newModFreq("FREQ",0x09,0.95),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,9.8),                          // Mod Delay
   newWavST  ("WAVE",0x0B,0),                            // Mod Waveform
   newRevTime("RT"  ,0x13,2.8),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.3),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,96),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,9e3),                          // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,53),                           // Reverb Balance
   new0To100 ("DPTH",0x1B,84),                           // Mod Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // RT, BAL, MIX

/* Reverb and symphonic effects in series...*/

  Effect(0x26,"Reverb>Symphonic","RT","BAL","MIX",       // Reverb->Symphonic
   newIniDlay("IDLY",0x08,12),                           // Initial Delay
   newModFreq("FREQ",0x09,1.45),                         // Mod Frequency
   newModDlay("MDT" ,0x0A,2.5),                          // Mod Delay
   newWavST  ("WAVE",0x0B,1),                            // Mod Waveform
   newRevTime("RT"  ,0x13,2.6),                          // Reverb Time
   newHiRatio("HRAT",0x14,0.3),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,100),                          // Reflection Density
   newHiPass ("HPF" ,0x17,63),                           // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,10e3),                         // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,55),                           // Reverb Balance
   new0To100 ("DPTH",0x1B,67),                           // Mod Depth
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // RT, BAL, MIX

/* Reverb and autopan effects in series..*/

  Effect(0x27,"Reverb>Pan","RT","BAL","MIX",             // Reverb->Pan
   newIniDlay("IDLY",0x08,30),                           // Initial Delay
   newModFreq("FREQ",0x09,0.65),                         // Mod Frequency
   newChoice ("WAVE",0x0B,0,"Sine","Triangle","Square"), // Mod Waveform
   newRevTime("RT"  ,0x13,6),                            // Reverb Time
   newHiRatio("HRAT",0x14,0.8),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x15,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x16,82),                           // Reflection Density
   newHiPass ("HPF" ,0x17),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x19,11.2e3),                       // Lo Pass Frequency
   new0To100 ("BAL" ,0x1A,30),                           // Reverb Balance
   new0To100 ("DPT" ,0x1B,98),                           // Mod Depth
   newPanDir ("DIR" ,0x1C),                              // Panning direction
   new0To100 ("MIX" ,0x1D,50)),                          // Mix
 //newKnobs  (0x13,0x1A,0x1D)),                          // RT, BAL, MIX

/* Delay and early reverberation reflections effects in parallel...*/

  Effect(0x28,"Delay+Reflections","LIVE","BAL","MIX",    // Delay+E.Ref.
   newFBGain ("FB",  0x00,8),                            // Feedback Gain
   newIniDlay("IDLY",0x01,25),                           // Initial Delay
   newLinear ("DT.L",0x08,0,0.1,1000,600),               // Delay Time [L]
   newLinear ("DT.R",0x09,0,0.1,1000,300),               // Delay Time [R]
   newLinear ("DTFB",0x0A,0,0.1,1000,600),               // Delay Time [F]
   newERType ("TYPE",0x0C,1),                            // Room Type
   newHiRatio("HRAT",0x13,0.8),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15),                              // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50),                           // Mix
   new0To100 ("BAL" ,0x16,64),                           // Reverb Balance
   newLinear ("SIZE",0x1E,0.2,0.2,20,2.4),               // Room Size
   new0To10  ("LIVE",0x1F,4),                            // Room Liveness
   new0To10  ("DIFF",0x20,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x21,80),                           // Reflection Density
   newLinear ("ERNO",0x22,1,1,19,16)),                   // Reflection Count
 //newKnobs  (0x1F,0x16,0x1D)),                          // LIVE, BAL, MIX

/* Delay and early reverberation reflections effects in series...*/

  Effect(0x29,"Delay>Reflections","LIVE","BAL","MIX",    // Delay->E.Ref.
   newFBGain ("FB",  0x00,48),                           // Feedback Gain
   newIniDlay("IDLY",0x01,10),                           // Initial Delay
   newLinear ("DT.L",0x08,0,0.1,1000,250),               // Delay Time [L]
   newLinear ("DT.R",0x09,0,0.1,1000,500),               // Delay Time [R]
   newLinear ("DTFB",0x0A,0,0.1,1000,500),               // Delay Time [F]
   newERType ("TYPE",0x0C,1),                            // Room Type
   newHiRatio("HRAT",0x13,0.4),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15),                              // Lo Pass Frequency
   new0To100 ("MIX" ,0x1D,50),                           // Mix
   new0To100 ("BAL" ,0x16,30),                           // Reverb Balance
   newLinear ("SIZE",0x1E,0.2,0.2,20,2.2),               // Room Size
   new0To10  ("LIVE",0x1F,4),                            // Room Liveness
   new0To10  ("DIFF",0x20,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x21,90),                           // Reflection Density
   newLinear ("ERNO",0x22,1,1,19,18)),                   // Reflection Count
 //newKnobs  (0x1F,0x16,0x1D)),                          // LIVE, BAL, MIX

/* Delay and reverb effects in parallel...*/

  Effect(0x2A,"Delay+Reverb","RT","BAL","MIX",           // Delay+Reverb
   newFBGain ("FB",  0x00,47),                           // Feedback Gain
   newIniDlay("IDLY",0x01,25),                           // Initial Delay
   newLinear ("DT.L",0x08,0,0.1,1000,410),               // Delay Time [L]
   newLinear ("DT.R",0x09,0,0.1,1000,398),               // Delay Time [R]
   newLinear ("DTFB",0x0A,0,0.1,1000,410),               // Delay Time [F]
   newHiRatio("HRAT",0x13,0.1),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15,3.15e3),                       // Lo Pass Frequency
   new0To100 ("BAL" ,0x16,46),                           // Reverb Balance
   new0To100 ("MIX" ,0x1D,50),                           // Mix
   newRevTime("RT"  ,0x1E,3.6),                          // Reverb Time
   newHiRatio("R.HI",0x1F,0.7),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x20,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x21,94)),                          // Reflection Density
 //newKnobs  (0x1E,0x16,0x1D)),                          // RT, BAL, MIX

/* Delay and reverb effects in series...*/

  Effect(0x2B,"Delay>Reverb","RT","BAL","MIX",           // Delay->Reverb
   newFBGain ("FB",  0x00,12),                           // Feedback Gain
   newIniDlay("IDLY",0x01,16),                           // Initial Delay
   newLinear ("DT.L",0x08,0,0.1,1000,43),                // Delay Time [L]
   newLinear ("DT.R",0x09,0,0.1,1000,50),                // Delay Time [R]
   newLinear ("DTFB",0x0A,0,0.1,1000,50),                // Delay Time [F]
   newHiRatio("HRAT",0x13,0.8),                          // Hi Freq Ratio
   newHiPass ("HPF" ,0x14),                              // Hi Pass Frequency
   newLoPass ("LPF" ,0x15,5.3e3),                        // Lo Pass Frequency
   new0To100 ("BAL" ,0x16,46),                           // Reverb Balance
   new0To100 ("MIX" ,0x1D,50),                           // Mix
   newRevTime("RT"  ,0x1E,1.4),                          // Reverb Time
   newHiRatio("R.HI",0x1F,0.6),                          // Hi Freq Ratio
   new0To10  ("DIFF",0x20,10),                           // Stereo Diffusion
   new0To100 ("DNST",0x21,70)),                          // Reflection Density
 //newKnobs  (0x1E,0x16,0x1D)),                          // RT, BAL, MIX

/* Digital distortion and delay effects in series...*/

  Effect(0x2C,"Distortion>Delay","DRV","DIST","BAL",     // Dist->Delay
   newLinear ("DT",  0x08,0,0.2,2725,385),               // Delay Time
   newFBGain ("FB",  0x09,30),                           // Feedback Gain
   newModFreq("FREQ",0x0A,0.35),                         // Mod Frequency
   newDigType("TYPE",0x0B),                              // Distortion Type
   new0To100 ("DRV", 0x13,90),                           // Distortion Drive
   new0To100 ("MSTR",0x14,40),                           // Master Volume
   newLinear ("TONE",0x15,-10,1,+10,-6),                 // Master Tine
   newLinear ("NG"  ,0x16,0,1,20,3),                     // Noise Gate
   newHiRatio("HRAT",0x19,0.3),                          // Hi Freq Ratio
   new0To100 ("DPTH",0x1A,78),                           // Mod Depth
   new0To100 ("BAL" ,0x1B,23)),                          // Reverb Balance
 //newKnobs  (0x13,0x14,0x1B)),                          // DRV, MSTR, BAL

/* Three-band filter...*/

  Effect(0x2D,"Filter[Multi]","LVL1","LVL2","LVL3",      // MultiFilter
   newFltType("TYP1",0x0B,0),                            // Filt Type      [1]
   newFltType("TYP2",0x0C,2),                            // Filt Type      [2]
   newFltType("TYP3",0x0D,1),                            // Filt Type      [3]
   newFreqcy ("FRQ1",0x13,28,16e3,110,2e3),              // Filt Frequency [1]
   new0To100 ("LVL1",0x14,100),                          // Filt Level     [1]
   new0To20  ("RES1",0x15,2),                            // Filt Resonance [1]
   new0To100 ("MIX" ,0x1D,50),                           // Mix
   newFreqcy ("FRQ2",0x1E,28,16e3,110,1e3),              // Filt Frequency [2]
   new0To100 ("LVL2",0x1F,100),                          // Filt Level     [2]
   new0To20  ("RES2",0x20,2),                            // Filt Resonance [2]
   newFreqcy ("FRQ3",0x29,28,16e3,110,900),              // Filt Frequency [3]
   new0To100 ("LVL3",0x2A,100),                          // Filt Level     [3]
   new0To20  ("RES3",0x2B,2)),                           // Filt Resonance [3]
 //newKnobs  (0x14,0x1F,0x2A)),                          // LVL1, LVL2, LVL3

/* 3-band dynamics processor, with individual solo and gain reduction metering
   for each band...*/

  Effect(0x2E,"Dynamics","LO.G","MI.G","HI.G",           // MultiBandDyna
   new0To100 ("LKUP",0x00),                              // Lookup Delay
   newLinear ("CTHR",0x01,-24,0.1,0),                    // Compressor Threshold
   newLinear ("EXPT",0x02,-54,0.1,24),                   // Expander Threshold
   newLinear ("LIMT",0x03,-12,0.1,0,0),                  // Limiter Threshold
   newLinear ("LO.G",0x08,-96,0.1,+12,0),                // Low  Gain
   newLinear ("MI.G",0x09,-96,0.1,+12,0),                // Mid  Gain
   newLinear ("HI.G",0x0A,-96,0.1,+12,0),                // High Gain
   newLinear ("SLOP",0x0B,-6,-6,-12),                    // Filter Slope
   newChoice ("COMP",0x0C,"Off","On"),                   // Compressor Switch
   newChoice ("EXP" ,0x0D,"Off","On"),                   // Expander Switch
   newChoice ("LIM", 0x0E,"Off","On"),                   // Limiter Switch
   newLinear ("CEIL",0x14,-6,0.1,0.1,0.1),               // Maximum Output
   newFreqcy ("L-MX",0x15,21.2,8e3,103,200),             // Low/Mid  Frequency
   newFreqcy ("M-HX",0x16,21.2,8e3,103,2e3),             // Mid/High Frequency
   newLinear ("PRE", 0x17,-10,1,+10,0),                  // Presence
   newRatio  ("CRAT",0x1E,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x1F,0,1,120,10),                   // Compressor Atack
   newRelTime("CREL",0x20,52),                           // Compressor Release
   newLinear ("CKNE",0x21,0,1,5),                        // Compressor Knee
   newRatio  ("ERAT",0x29,1),                            // Expander Ratio
   newRelTime("EREL",0x2B,52),                           // Expander Release
   newLinear ("LATA",0x35,0,1,120),                      // Limiter Atack
   newRelTime("LREL",0x36,52),                           // Limiter Release
   newLinear ("LKNE",0x37,0,1,5)),                       // Limiter Knee
 //newKnobs  (0x08,0x09,0x0A)),                          // LO.G, MI.G, HI.G

/* Advanced distortion effect...*/

  Effect(0x2F,"Distortion","GAIN","MSTR","FB",           // Distortion
   newLevel  ("GAIN",0x08,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x09,5.5),                          // Master Volume
   newLevel  ("TONE",0x0A,10),                           // Master Tine
   newDstType("TYPE",0x0B),                              // Distortion Type
   newFreqcy ("EQ1F",0x00,50,400,127),                   // Post Frequency [1]
   newEQGain ("EQ1G",0x13),                              // Post Gain      [1]
   newFreqcy ("EQ1Q",0x14,0.1,20,60),                    // Post Bandwidth [1]
   newFreqcy ("EQ2F",0x15,200,1.6e3,127),                // Post Frequency [2]
   newEQGain ("EQ2G",0x16),                              // Post Gain      [2]
   newFreqcy ("EQ2Q",0x17,0.1,20,60),                    // Post Bandwidth [2]
   newFreqcy ("EQ3F",0x18,600,4.8e3,127),                // Post Frequency [3]
   newEQGain ("EQ3G",0x19),                              // Post Gain      [3]
   newFreqcy ("EQ3Q",0x1A,0.1,20,60),                    // Post Bandwidth [3]
   newFreqcy ("EQ4F",0x1B,2e3,16e3,127),                 // Post Frequency [4]
   newEQGain ("EQ4G",0x1C),                              // Post Gain      [4]
   newFreqcy ("EQ4Q",0x1D,0.1,20,60),                    // Post Bandwidth [4]
   newLevel  ("PELV",0x01,10),                           // Pre  EQ Level
   newFreqcy ("PE1F",0x20,50,500,127),                   // Pre  Frequency [1]
   newEQGain ("PE1G",0x21),                              // Pre  Gain      [1]
   newFreqcy ("PE1Q",0x22,0.1,20,60),                    // Pre  Bandwidth [1]
   newFreqcy ("PE2F",0x23,200,2e3,127),                  // Pre  Frequency [2]
   newEQGain ("PE2G",0x24),                              // Pre  Gain      [2]
   newFreqcy ("PE2Q",0x25,0.1,20,60),                    // Pre  Bandwidth [2]
   newFreqcy ("PE3F",0x26,1e3,10e3,127),                 // Pre  Frequency [3]
   newEQGain ("PE3G",0x27),                              // Pre  Gain      [3]
   newFreqcy ("PE3Q",0x28,0.1,20,60),                    // Pre  Bandwidth [3]
   newLevel  ("NGTH",0x2A,0),                            // Gate Threshold
   newAttGate("NGAT",0x2B,4),                            // Gate Attack
   newHldGate("NGHL",0x2C),                              // Gate Hold Level
   newDcyGate("NGDC",0x2D)),                             // Gate Decay Time
 //newKnobs  (0x08,0x09,0x0A)),                          // GAIN, MSTR, TONE

/* Vintage flanger effect simulation,..*/

  Effect(0x30,"Flanger[Vintage]","SPD","DPTH","FB",      // VintageFlanger
   newLevel  ("SPD", 0x00,1),                            // Flanger Speed
   newLinear ("TYPE",0x0B,1,1,3),                        // Flanger Type
   newLevel  ("DPTH",0x13,5),                            // Flanger Depth
   newLevel  ("MANU",0x14,5),                            // Flanger Delay
   newLevel  ("FB",  0x15,5),                            // Flanger Feedback
   newLevel  ("SPRD",0x16,5),                            // Flanger Spread
   new0To100 ("MIX" ,0x17,50)),                          // Mix
 //newKnobs  (0x00,0x13,0x15)),                          // SPD, DPTH, FB

/* Vintage monophonic phase shifter effect simulation...*/

  Effect(0x31,"Phaser[Vintage1]","SPD","DPTH","FB",      // MonoVntgPhaser
   newLinear ("MODE",0x00,1,1,2),                        // Phaser Mode
   newLinear ("STAG",0x0B,4,2,14),                       // Phaser Stages
   newLevel  ("SPD" ,0x13,1.0),                          // Mod Speed
   newLevel  ("DPTH",0x14,5.0),                          // Mod Depth
   newLevel  ("MANU",0x15,5.0),                          // Manual
   newLevel  ("FB"  ,0x16,4.0),                          // Feedback Gain
   newLevel  ("CLOR",0x17,5.0)),                         // Effect Color
 //newKnobs  (0x13,0x14,0x16)),                          // SPD, DPTH, FB

/* Vintage stereophonic phase shifter effect simulation...*/

  Effect(0x32,"Phaser[Vintage2]","SPD","DPTH","FB",      // StereoVntgPhaser
   newLinear ("MODE",0x00,1,1,2),                        // Phaser Mode
   newLinear ("STAG",0x0B,4,2,10),                       // Phaser Stages
   newLevel  ("SPD" ,0x13,2.0),                          // Mod Speed
   newLevel  ("DPTH",0x14,5.0),                          // Mod Depth
   newLevel  ("MANU",0x15,5.0),                          // Manual
   newLevel  ("FB"  ,0x16,5.0),                          // Feedback Gain
   newLevel  ("CLOR",0x17,5.0),                          // Effect Color
   newLevel  ("SPRD",0x18,5.0)),                         // Stereo Spread
 //newKnobs  (0x13,0x14,0x16)),                          // SPD, DPTH, FB

/* Three-band parametric equalizer,..*/

  Effect(0x33,"ParametricEQ","EQ1F","EQ1G","EQ1Q",       // 3BandParaEQ
   newLevel  ("LEVL",0x00,10),                           // Effect Level
   newFreqcy ("EQ1F",0x08,20,20e3,255),                  // EQ Frequency [1]
   newFreqcy ("EQ2F",0x09,20,20e3,255),                  // EQ Frequency [2]
   newFreqcy ("EQ3F",0x0A,20,20e3,255),                  // EQ Frequency [3]
   newEQGain ("EQ1G",0x15),                              // EQ Gain      [1]
   newEQGain ("EQ2G",0x16),                              // EQ Gain      [2]
   newEQGain ("EQ3G",0x17),                              // EQ Gain      [3]
   newFreqcy ("EQ1Q",0x1B,0.1,20,127,1.03),              // EQ Bandwidth [1]
   newFreqcy ("EQ2Q",0x1C,0.1,20,127),                   // EQ Bandwidth [2]
   newFreqcy ("EQ3Q",0x1D,0.1,20,127)),                  // EQ Bandwidth [3]
 //newKnobs  (0x08,0x15,0x1B)),                          // EQ1F, EQ1G, EQ1Q

/* Spring reverb simulation...*/

  Effect(0x34,"Reverb[Spring]","REV","NONE","NONE",      // SpringReverb
   newLevel  ("REV" ,0x00,2)),                           // Reverb Level
 //newKnobs  (0x00)),                                    // REV

/* Vintage tape echo simulation...*/

  Effect(0x35,"Echo[Tape]","DT","FB","MIX",              // TapeEcho
   newLevel  ("DT"  ,0x00,4.6),                          // Delay Time
   newLevel  ("FB"  ,0x13,5.0),                          // Feedback Level
   newLevel  ("MIX" ,0x14,7.0)),                         // Mix
 //newKnobs  (0x00,0x13,0x14)),                          // DT, FB, MIX

/* Compressor effect...*/

  Effect(0x36,"Compressor","RATI","ATAK","GAIN",         // Compressor
   newLinear ("THRE",0x00,-54,0.1,0,-8),                 // Threshold Level`
   newRatio  ("RATI",0x13,3.5),                          // Ratio
   newLinear ("ATAK",0x14,0,1,120,7),                    // Atack Time
   newRelTime("RELE",0x15,261),                          // Release Time
   newLinear ("KNEE",0x16,0,1,5,4),                      // Knee
   newLinear ("GAIN",0x17,0,0.5,18,2.5)),                // Gain
 //newKnobs  (0x13,0x14,0x17)),                          // RATI, ATAK, GAIN

/* Effect chain comprising compressor, amplifier simulation, chorus, delay,
   and reverb effects...*/

  Effect(0x37,"Amp[Chorus]","GAIN","MSTR","TONE",        // AmpMulti(Cho)
   newAmpType("AMP" ,0x0B),                              // Amplifier Type
   newSpkType("SP"  ,0x0C),                              // Speaker Type
   newLevel  ("GAIN",0x13,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x14,5.5),                          // Master Volume
   newLevel  ("TONE",0x17,10),                           // Master Tone
   newLevel  ("TRE" ,0x19,5),                            // Treble
   newLevel  ("HMID",0x1A,5),                            // Hi Mid
   newLevel  ("LMID",0x1B,5),                            // Lo Mid
   newLevel  ("BASS",0x1C,5),                            // Bass
   newLevel  ("PRE" ,0x1D,5),                            // Presence
   newLevel  ("NGTH",0x1F,0),                            // Gate Threshold
   newAttGate("NGAT",0x20,4),                            // Gate Attack
   newHldGate("NGHL",0x21),                              // Gate Hold Level
   newDcyGate("NGDC",0x22),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Chorus Waveform
   newLinear ("CHDT",0x03,0,0.1,30,6),                   // Chorus Delay Time
   newLevel  ("CHSP",0x34),                              // Chorus Speed
   newLevel  ("CHDP",0x35),                              // Chorus Depth
   newLevel  ("CHLV",0x36),                              // Chorus Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x13,0x14,0x17)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, amplifier simulation, flanger, delay,
   and reverb effects...*/

  Effect(0x38,"Amp[Flanger]","GAIN","MSTR","TONE",       // AmpMulti(Flng)
   newAmpType("AMP" ,0x0B),                              // Amplifier Type
   newSpkType("SP"  ,0x0C),                              // Speaker Type
   newLevel  ("GAIN",0x13,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x14,5.5),                          // Master Volume
   newLevel  ("TONE",0x17,10),                           // Master Tone
   newLevel  ("TRE" ,0x19,5),                            // Treble
   newLevel  ("HMID",0x1A,5),                            // Hi Mid
   newLevel  ("LMID",0x1B,5),                            // Lo Mid
   newLevel  ("BASS",0x1C,5),                            // Bass
   newLevel  ("PRE" ,0x1D,5),                            // Presence
   newLevel  ("NGTH",0x1F,0),                            // Gate Threshold
   newAttGate("NGAT",0x20,4),                            // Gate Attack
   newHldGate("NGHL",0x21),                              // Gate Hold Level
   newDcyGate("NGDC",0x22),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Flanger Waveform
   newLinear ("FLDT",0x03,0,0.1,10,1),                   // Flanger Delay Time
   newLevel  ("FLSP",0x34),                              // Flanger Speed
   newLevel  ("FLDP",0x35),                              // Flanger Depth
   newFBGain2("FLFB",0x36,-80),                          // Flanger Feedback
   newLevel  ("FLVL",0x37),                              // Flanger Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x13,0x14,0x17)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, amplifier simulation, tremolo, delay,
   and reverb effects...*/

  Effect(0x39,"Amp[Tremolo]","GAIN","MSTR","TONE",       // AmpMulti(Trem)
   newAmpType("AMP" ,0x0B),                              // Amplifier Type
   newSpkType("SP"  ,0x0C),                              // Speaker Type
   newLevel  ("GAIN",0x13,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x14,5.5),                          // Master Volume
   newLevel  ("TONE",0x17,10),                           // Master Tone
   newLevel  ("TRE" ,0x19,5),                            // Treble
   newLevel  ("HMID",0x1A,5),                            // Hi Mid
   newLevel  ("LMID",0x1B,5),                            // Lo Mid
   newLevel  ("BASS",0x1C,5),                            // Bass
   newLevel  ("PRE" ,0x1D,5),                            // Presence
   newLevel  ("NGTH",0x1F,0),                            // Gate Threshold
   newAttGate("NGAT",0x20,4),                            // Gate Attack
   newHldGate("NGHL",0x21),                              // Gate Hold Level
   newDcyGate("NGDC",0x22),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E,1),                            // Tremolo Waveform
   newLevel  ("TRSP",0x34),                              // Tremolo Speed
   newLevel  ("TRDP",0x35),                              // Tremolo Depth
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x13,0x14,0x17)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, amplifier simulation, phaser, delay,
   and reverb effects...*/

  Effect(0x3A,"Amp[Phaser]","GAIN","MSTR","TONE",        // AmpMulti(Phas)
   newAmpType("AMP" ,0x0B),                              // Amplifier Type
   newSpkType("SP"  ,0x0C),                              // Speaker Type
   newLevel  ("GAIN",0x13,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x14,5.5),                          // Master Volume
   newLevel  ("TONE",0x17,10),                           // Master Tone
   newLevel  ("TRE" ,0x19,5),                            // Treble
   newLevel  ("HMID",0x1A,5),                            // Hi Mid
   newLevel  ("LMID",0x1B,5),                            // Lo Mid
   newLevel  ("BASS",0x1C,5),                            // Bass
   newLevel  ("PRE" ,0x1D,5),                            // Presence
   newLevel  ("NGTH",0x1F,0),                            // Gate Threshold
   newAttGate("NGAT",0x20,4),                            // Gate Attack
   newHldGate("NGHL",0x21),                              // Gate Hold Level
   newDcyGate("NGDC",0x22),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Phaser Waveform
   newLevel  ("PHSP",0x34),                              // Phaser Speed
   newLevel  ("PHDP",0x35),                              // Phaser Depth
   newFBGain2("PHFB",0x36,50),                           // Phaser Feedback
   newLevel  ("PHVL",0x37),                              // Phaser Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x13,0x14,0x17)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, distortion, chorus, delay, and reverb
   effects...*/

  Effect(0x3B,"Distortion[Chorus]","GAIN","MSTR","TONE", // DistMulti(Cho)
   newDstType("TYPE",0x0B),                              // Distortion Type
   newLevel  ("GAIN",0x08,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x09,5.5),                          // Master Volume
   newLevel  ("TONE",0x0A,10),                           // Master Tine
   newFreqcy ("EQ1F",0x00,50,400,127),                   // Post Frequency [1]
   newEQGain ("EQ1G",0x13),                              // Post Gain      [1]
   newFreqcy ("EQ1Q",0x14,0.1,20,60),                    // Post Bandwidth [1]
   newFreqcy ("EQ2F",0x15,200,1.6e3,127),                // Post Frequency [2]
   newEQGain ("EQ2G",0x16),                              // Post Gain      [2]
   newFreqcy ("EQ2Q",0x17,0.1,20,60),                    // Post Bandwidth [2]
   newFreqcy ("EQ3F",0x18,600,4.8e3,127),                // Post Frequency [3]
   newEQGain ("EQ3G",0x19),                              // Post Gain      [3]
   newFreqcy ("EQ3Q",0x1A,0.1,20,60),                    // Post Bandwidth [3]
   newFreqcy ("EQ4F",0x1B,2e3,16e3,127),                 // Post Frequency [4]
   newEQGain ("EQ4G",0x1C),                              // Post Gain      [4]
   newFreqcy ("EQ4Q",0x1D,0.1,20,60),                    // Post Bandwidth [4]
   newLevel  ("PELV",0x01,10),                           // Pre  EQ Level
   newFreqcy ("PE1F",0x20,50,500,127),                   // Pre  Frequency [1]
   newEQGain ("PE1G",0x21),                              // Pre  Gain      [1]
   newFreqcy ("PE1Q",0x22,0.1,20,60),                    // Pre  Bandwidth [1]
   newFreqcy ("PE2F",0x23,200,2e3,127),                  // Pre  Frequency [2]
   newEQGain ("PE2G",0x24),                              // Pre  Gain      [2]
   newFreqcy ("PE2Q",0x25,0.1,20,60),                    // Pre  Bandwidth [2]
   newFreqcy ("PE3F",0x26,1e3,10e3,127),                 // Pre  Frequency [3]
   newEQGain ("PE3G",0x27),                              // Pre  Gain      [3]
   newFreqcy ("PE3Q",0x28,0.1,20,60),                    // Pre  Bandwidth [3]
   newLevel  ("NGTH",0x30,0),                            // Gate Threshold
   newAttGate("NGAT",0x31,4),                            // Gate Attack
   newHldGate("NGHL",0x32),                              // Gate Hold Level
   newDcyGate("NGDC",0x33),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Chorus Waveform
   newLinear ("CHDT",0x03,0,0.1,30,6),                   // Chorus Delay Time
   newLevel  ("CHSP",0x34),                              // Chorus Speed
   newLevel  ("CHDP",0x35),                              // Chorus Depth
   newLevel  ("CHLV",0x36),                              // Chorus Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x08,0x09,0x0A)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, distortion, flanger, delay, and reverb
   effects...*/

  Effect(0x3C,"Distortion[Flanger]","GAIN","MSTR","TONE",// DistMulti(Flng)
   newDstType("TYPE",0x0B),                              // Distortion Type
   newLevel  ("GAIN",0x08,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x09,5.5),                          // Master Volume
   newLevel  ("TONE",0x0A,10),                           // Master Tine
   newFreqcy ("EQ1F",0x00,50,400,127),                   // Post Frequency [1]
   newEQGain ("EQ1G",0x13),                              // Post Gain      [1]
   newFreqcy ("EQ1Q",0x14,0.1,20,60),                    // Post Bandwidth [1]
   newFreqcy ("EQ2F",0x15,200,1.6e3,127),                // Post Frequency [2]
   newEQGain ("EQ2G",0x16),                              // Post Gain      [2]
   newFreqcy ("EQ2Q",0x17,0.1,20,60),                    // Post Bandwidth [2]
   newFreqcy ("EQ3F",0x18,600,4.8e3,127),                // Post Frequency [3]
   newEQGain ("EQ3G",0x19),                              // Post Gain      [3]
   newFreqcy ("EQ3Q",0x1A,0.1,20,60),                    // Post Bandwidth [3]
   newFreqcy ("EQ4F",0x1B,2e3,16e3,127),                 // Post Frequency [4]
   newEQGain ("EQ4G",0x1C),                              // Post Gain      [4]
   newFreqcy ("EQ4Q",0x1D,0.1,20,60),                    // Post Bandwidth [4]
   newLevel  ("PELV",0x01,10),                           // Pre  EQ Level
   newFreqcy ("PE1F",0x20,50,500,127),                   // Pre  Frequency [1]
   newEQGain ("PE1G",0x21),                              // Pre  Gain      [1]
   newFreqcy ("PE1Q",0x22,0.1,20,60),                    // Pre  Bandwidth [1]
   newFreqcy ("PE2F",0x23,200,2e3,127),                  // Pre  Frequency [2]
   newEQGain ("PE2G",0x24),                              // Pre  Gain      [2]
   newFreqcy ("PE2Q",0x25,0.1,20,60),                    // Pre  Bandwidth [2]
   newFreqcy ("PE3F",0x26,1e3,10e3,127),                 // Pre  Frequency [3]
   newEQGain ("PE3G",0x27),                              // Pre  Gain      [3]
   newFreqcy ("PE3Q",0x28,0.1,20,60),                    // Pre  Bandwidth [3]
   newLevel  ("NGTH",0x30,0),                            // Gate Threshold
   newAttGate("NGAT",0x31,4),                            // Gate Attack
   newHldGate("NGHL",0x32),                              // Gate Hold Level
   newDcyGate("NGDC",0x33),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Flanger Waveform
   newLinear ("FLDT",0x03,0,0.1,10,1),                   // Flanger Delay Time
   newLevel  ("FLSP",0x34),                              // Flanger Speed
   newLevel  ("FLDP",0x35),                              // Flanger Depth
   newFBGain2("FLFB",0x36,-80),                          // Flanger Feedback
   newLevel  ("FLVL",0x37),                              // Flanger Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x08,0x09,0x0A)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, distortion, tremolo, delay, and reverb
   effects...*/

  Effect(0x3D,"Distortion[Tremolo]","GAIN","MSTR","TONE",// DistMulti(Trem)
   newDstType("TYPE",0x0B),                              // Distortion Type
   newLevel  ("GAIN",0x08,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x09,5.5),                          // Master Volume
   newLevel  ("TONE",0x0A,10),                           // Master Tine
   newFreqcy ("EQ1F",0x00,50,400,127),                   // Post Frequency [1]
   newEQGain ("EQ1G",0x13),                              // Post Gain      [1]
   newFreqcy ("EQ1Q",0x14,0.1,20,60),                    // Post Bandwidth [1]
   newFreqcy ("EQ2F",0x15,200,1.6e3,127),                // Post Frequency [2]
   newEQGain ("EQ2G",0x16),                              // Post Gain      [2]
   newFreqcy ("EQ2Q",0x17,0.1,20,60),                    // Post Bandwidth [2]
   newFreqcy ("EQ3F",0x18,600,4.8e3,127),                // Post Frequency [3]
   newEQGain ("EQ3G",0x19),                              // Post Gain      [3]
   newFreqcy ("EQ3Q",0x1A,0.1,20,60),                    // Post Bandwidth [3]
   newFreqcy ("EQ4F",0x1B,2e3,16e3,127),                 // Post Frequency [4]
   newEQGain ("EQ4G",0x1C),                              // Post Gain      [4]
   newFreqcy ("EQ4Q",0x1D,0.1,20,60),                    // Post Bandwidth [4]
   newLevel  ("PELV",0x01,10),                           // Pre  EQ Level
   newFreqcy ("PE1F",0x20,50,500,127),                   // Pre  Frequency [1]
   newEQGain ("PE1G",0x21),                              // Pre  Gain      [1]
   newFreqcy ("PE1Q",0x22,0.1,20,60),                    // Pre  Bandwidth [1]
   newFreqcy ("PE2F",0x23,200,2e3,127),                  // Pre  Frequency [2]
   newEQGain ("PE2G",0x24),                              // Pre  Gain      [2]
   newFreqcy ("PE2Q",0x25,0.1,20,60),                    // Pre  Bandwidth [2]
   newFreqcy ("PE3F",0x26,1e3,10e3,127),                 // Pre  Frequency [3]
   newEQGain ("PE3G",0x27),                              // Pre  Gain      [3]
   newFreqcy ("PE3Q",0x28,0.1,20,60),                    // Pre  Bandwidth [3]
   newLevel  ("NGTH",0x30,0),                            // Gate Threshold
   newAttGate("NGAT",0x31,4),                            // Gate Attack
   newHldGate("NGHL",0x32),                              // Gate Hold Level
   newDcyGate("NGDC",0x33),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E,1),                            // Tremolo Waveform
   newLevel  ("TRSP",0x34),                              // Tremolo Speed
   newLevel  ("TRDP",0x35),                              // Tremolo Depth
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x08,0x09,0x0A)),                          // GAIN, MSTR, TONE

/* Effect chain comprising compressor, distortion, phaser, delay, and reverb
   effects...*/

  Effect(0x3E,"Distortion[Phaser]","GAIN","MSTR","TONE", // DistMulti(Phas)
   newDstType("TYPE",0x0B),                              // Distortion Type
   newLevel  ("GAIN",0x08,7.5),                          // Preamp Gain
   newLevel  ("MSTR",0x09,5.5),                          // Master Volume
   newLevel  ("TONE",0x0A,10),                           // Master Tine
   newFreqcy ("EQ1F",0x00,50,400,127),                   // Post Frequency [1]
   newEQGain ("EQ1G",0x13),                              // Post Gain      [1]
   newFreqcy ("EQ1Q",0x14,0.1,20,60),                    // Post Bandwidth [1]
   newFreqcy ("EQ2F",0x15,200,1.6e3,127),                // Post Frequency [2]
   newEQGain ("EQ2G",0x16),                              // Post Gain      [2]
   newFreqcy ("EQ2Q",0x17,0.1,20,60),                    // Post Bandwidth [2]
   newFreqcy ("EQ3F",0x18,600,4.8e3,127),                // Post Frequency [3]
   newEQGain ("EQ3G",0x19),                              // Post Gain      [3]
   newFreqcy ("EQ3Q",0x1A,0.1,20,60),                    // Post Bandwidth [3]
   newFreqcy ("EQ4F",0x1B,2e3,16e3,127),                 // Post Frequency [4]
   newEQGain ("EQ4G",0x1C),                              // Post Gain      [4]
   newFreqcy ("EQ4Q",0x1D,0.1,20,60),                    // Post Bandwidth [4]
   newLevel  ("PELV",0x01,10),                           // Pre  EQ Level
   newFreqcy ("PE1F",0x20,50,500,127),                   // Pre  Frequency [1]
   newEQGain ("PE1G",0x21),                              // Pre  Gain      [1]
   newFreqcy ("PE1Q",0x22,0.1,20,60),                    // Pre  Bandwidth [1]
   newFreqcy ("PE2F",0x23,200,2e3,127),                  // Pre  Frequency [2]
   newEQGain ("PE2G",0x24),                              // Pre  Gain      [2]
   newFreqcy ("PE2Q",0x25,0.1,20,60),                    // Pre  Bandwidth [2]
   newFreqcy ("PE3F",0x26,1e3,10e3,127),                 // Pre  Frequency [3]
   newEQGain ("PE3G",0x27),                              // Pre  Gain      [3]
   newFreqcy ("PE3Q",0x28,0.1,20,60),                    // Pre  Bandwidth [3]
   newLevel  ("NGTH",0x30,0),                            // Gate Threshold
   newAttGate("NGAT",0x31,4),                            // Gate Attack
   newHldGate("NGHL",0x32),                              // Gate Hold Level
   newDcyGate("NGDC",0x33),                              // Gate Decay Time
   newLinear ("CTHR",0x02,-54,0.1,0,-8),                 // Compressor Threshold
   newRatio  ("CRAT",0x29,3.5),                          // Compressor Ratio
   newLinear ("CATT",0x2A,0,1,120,7),                    // Compressor Atack
   newRelTime("CREL",0x2B,261),                          // Compressor Release
   newLinear ("CKNE",0x2C,0,1,5,4),                      // Compressor Knee
   newLinear ("CGAI",0x2D,0,0.5,18,2.5),                 // Compressor Gain
   newWavST  ("WAVE",0x0E),                              // Phaser Waveform
   newLevel  ("PHSP",0x34),                              // Phaser Speed
   newLevel  ("PHDP",0x35),                              // Phaser Depth
   newFBGain2("PHFB",0x36,50),                           // Phaser Feedback
   newLevel  ("PHVL",0x37),                              // Phaser Level
   newLinear ("DTFB",0x04,0,0.1,1000,300),               // Delay Time [F]
   new0To100 ("DT.L",0x3F,100),                          // Delay Tap  [L]
   new0To100 ("DT.R",0x40,100),                          // Delay Tap  [R]
   newFBGain2("D.FB",0x41),                              // Delay Feedback
   newHiRatio("D.HI",0x42,0.8),                          // Delay HF Ratio
   newLevel  ("DLVL",0x43),                              // Delay Level
   newHiPass ("DHPF",0x47),                              // Delay HP Frequency
   newLoPass ("DLPF",0x48),                              // Delay LP Frequency
   newIniDlay("RIDL",0x05,4.6),                          // Reverb Ini Delay
   newRevTime("RT"  ,0x4A,4.7),                          // Reverb Time
   newHiRatio("R.HI",0x4B,0.6),                          // Reverb HF Ratio
   new0To10  ("RDIF",0x4C,5),                            // Reverb Diffusion
   new0To100 ("RDNS",0x4D,90),                           // Reverb Density
   newLevel  ("RLVL",0x4E)),                             // Reverb Balance
 //newKnobs  (0x08,0x09,0x0A)),                          // GAIN, MSTR, TONE

/* A multi-effect chain designed for use with electric bass guitars...*/

  Effect(0x3F,"Bass","CRAT","GAIN","MSTR",               // BassPreamp
   newSimType("TYPE",0x0B),                              // Simulation Type
   newLevel  ("GAIN",0x08,5),                            // Preamp Gain
   newLevel  ("MSTR",0x09,5),                            // Master Volume
   newLinear ("BASS",0x13,-15,0.25,+15,0),               // Bass   Level
   newLinear ("LMID",0x14,-15,0.25,+15,0),               // Lo Mid Level
   newLinear ("MID" ,0x15,-15,0.25,+15,0),               // Mid    Level
   newLinear ("HMID",0x16,-15,0.25,+15,0),               // Hi Mid Level
   newLinear ("TRE" ,0x17,-15,0.25,+15,0),               // Treble Level
   newFreqcy ("BASF",0x19,20,320,127,79.1),              // Bass   Frequency
   newFreqcy ("LMDF",0x1A,80 ,1.28e3,127,317),           // Lo Mid Frequency
   newFreqcy ("MIDF",0x1B,250,4e3,127,989),              // Mid    Frequency
   newFreqcy ("HMDF",0x1C,500,8e3,127,4.95e3),           // Hi Mid Frequency
   newFreqcy ("TREF",0x1D,1.25e3,20e3,127,4.95e3),       // Treble Frequency
   newFreqcy ("PEQF",0x01,20,20e3,255,1.02e3),           // EQ Frequency
   newFreqcy ("PEQQ",0x1E,0.1,20,127,0.71),              // EQ Bandwidth
   newLinear ("PEQG",0x1F,-15,0.25,+15,0),               // EQ Gain
   newLinear ("NGAT",0x20,-54,1,0),                      // Gate Threshold
   newChoice ("SPSW",0x21,2,"Off","Right","LeftRight"),  // Speaker Switch
   newChoice ("OLIM",0x22,1,"Off","On"),                 // Limiter Switch
   newRatio  ("CRAT",0x23,1),                            // Compressor Ratio
   newLinear ("CTHR",0x24,-54,1,0,-18),                  // Compressor Threshold
   newLinear ("CATT",0x25,0,1,120,23),                   // Compressor Atack
   newRelTime("CREL",0x26,139),                          // Compressor Release
   newLinear ("CGAI",0x27,0,0.2,18,8.6),                 // Compressor Gain
   newLinear ("CKNE",0x28,0,1,5)))                       // Compressor Knee
 //newKnobs  (0x23,0x08,0x09)))                          // CRAT, GAIN MSTR

  val byname: Map[Name,Effect] = bykind.map(e ⇒ e.name.toUpperCase → e).toMap

  def help(): Unit = Utilities.tabulate(bykind.map(e ⇒ e.name))
}

//****************************************************************************
