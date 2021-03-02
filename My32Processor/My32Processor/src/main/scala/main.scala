/////////////////////////////////////////////
// My32-bit Processor Model Design By Chisel3
// My32Processor_Chisel3 / My32Processor_Chisel3.scala
//
// IEEE Standard Verilog® Hardware Description Language
// https://inst.eecs.berkeley.edu/~cs150/fa06/Labs/verilog-ieee.pdf
// 
// Hwacha Project (vector) / RoCC (Chipyard)
// https://github.com/ucb-bar/hwacha
// 
// https://chipyard.readthedocs.io/en/latest/Customization/RoCC-Accelerators.html
////////////////////////////////////////////

package my32Processor

import chisel3.util._

class 32_risc extends Module {
    val registerFile = Reg(Vec(32, UInt (32.W))) // 32 registers each 32-bits wide
    val io = IO(new Bundle {
        val a = Input(UInt (2.W))
        val b = Input(UInt (2.W))
        val out = Output(UInt (2.W))    
    })

    io.out := io.a & io.b
    printf("dut: %d %d %d\n" , io.a, io.b, io.out)
 }

 class CompA extends Module {
     val io = IO(new Bundle {
         val a = Input(UInt(8.W))    
         val b = Input(UInt(8.W))
         val x = Output(UInt(8.W))
         val y = Output(UInt(8.W))
         })
      // function of A
 }

class CompB extends Module {
    val io = IO(new Bundle {
        val in1 = Input(UInt(8.W))
        val in2 = Input(UInt(8.w))
        val out = Output(UInt(8.W))
    })
    // function of B
}

class CompC extends Module {
    val io = IO(new Bundle {
        val in_a = Input(UInt(8.W))
        val in_b = Input(UInt(8.W))
        val in_c = Input(UInt(8.W))
        val out_x = Output(UInt(8.W))
        val out_y = Output(UInt(8.W))    
    })

    // create components A and B 
    val compA = Module(new CompA())
    val CompB = Module(new CompB())

    // connect A
    compA.io.a := io.in_a
    compA.io.b := io.in_b
    io.out_x := compA.io.x
    // connect B
    compB.io.in1 := compA.io.y
    compB.io.in2 := io.in_c
    io.out_y := compB.io.out 
}

// Top Level Model
class TopLevel extends Module {
    val io = IO(new Bundle {
      
    })
}

// Alu Model
class Alu extends Module {
    val io = IO(new Bundle {
          val add = a + b // addition
          val sub = a - b // subtraction'
          val neg = -a // negate  
          val mul = a * b // multiplication
          val div = a / b // division
          val mod = a % b // modulo operation
    })
} 