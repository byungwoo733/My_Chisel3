////////////////////////////////////////////
class TopLevel extends Module {
val io = IO(new Bundle {
val in_a = Input(UInt (8.W))
val in_b = Input(UInt (8.W))
val in_c = Input(UInt (8.W))
val out_m = Output(UInt (8.W))
val out_n = Output(UInt (8.W))
})
// create C and D
val c = Module(new CompC ())
val d = Module(new CompD ())
// connect C
c.io.in_a := io.in_a
c.io.in_b := io.in_b
c.io.in_c := io.in_c
io.out_m := c.io.out_x
// connect D
d.io.in := c.io.out_y
io.out_n := d.io.out
}

//////////////////////////////////////////
class CompC extends Module {
val io = IO(new Bundle {
val in_a = Input(UInt (8.W))
val in_b = Input(UInt (8.W))
val in_c = Input(UInt (8.W))
val out_x = Output(UInt (8.W))
val out_y = Output(UInt (8.W))
})
// create components A and B
val compA = Module(new CompA ())
val compB = Module(new CompB ())
// connect A
compA.io.a := io.in_a
compA.io.b := io.in_b
io.out_x := compA.io.x
// connect B
compB.io.in1 := compA.io.y
compB.io.in2 := io.in_c
io.out_y := compB.io.out
}

////////////////////////////////
class Fetch extends Module {
val io = IO(new Bundle {
val instr = Output(UInt (32.W))
val pc = Output(UInt (32.W))
})
// ... Implementation od fetch
}

class Decode extends Module {
val io = IO(new Bundle {
val instr = Input(UInt (32.W))
val pc = Input(UInt (32.W))
val aluOp = Output(UInt (5.W))
val regA = Output(UInt (32.W))
val regB = Output(UInt (32.W))
})
// ... Implementation of decode
}

class Execute extends Module {
val io = IO(new Bundle {
val aluOp = Input(UInt (5.W))
val regA = Input(UInt (32.W))
val regB = Input(UInt (32.W))
val result = Output(UInt (32.W))
})
// ... Implementation of execute
}

val fetch = Module(new Fetch ())
val decode = Module(new Decode ())
val execute = Module(new Execute)
fetch.io <> decode.io
decode.io <> execute.io
io <> execute.io