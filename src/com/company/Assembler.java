package com.company;

public class Assembler extends Main {
    static boolean checkReg(String x) {
        boolean H = (Reg.containsKey(x));
        if (H == false) {

            return false;
        }
        return true;
    }

    static boolean checkLabel(String y) {
        boolean x = labels.containsKey(y + ':');
        if (x == false) {
            System.out.println(" Wrong Name of Label");
            return false;
        }
        return true;
    }

    static void Assempler() {
        for (int i = 0; i < action.size(); i++) {
            if(action.get(i).matches("add|sub|or|and|slt")) {
                if (!checkReg(outRegister.get(i)) || !checkReg(inRegister.get(i)) || !checkReg(inRegister2.get(i))) {
                    System.out.println("Wrong Name of Register");
                    return;
                }
            }
            if(action.get(i).matches("addi|ori|andi|slti|bne|beq|sll")) {
                if (!checkReg(outRegister.get(i)) || !checkReg(inRegister.get(i))) {
                    System.out.println("Wrong Name of Register");
                    return;
                }
            }
            if(action.get(i).matches("lui|jr")) {
                if (!checkReg(outRegister.get(i))) {
                    System.out.println("Wrong Name of Register");
                    return;
                }
            }
            if (action.get(i).equals("addi"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i))+Integer.parseInt(inRegister2.get(i)));
            }
            else if (action.get(i).equals("add"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i))+Reg.get(inRegister2.get(i)));
            }
            else if (action.get(i).equals("sub"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i))-Reg.get(inRegister2.get(i)));
            }
            else if (action.get(i).equals("or"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i)) | Reg.get(inRegister2.get(i)));
            }
            else if (action.get(i).equals("and"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i)) & Reg.get(inRegister2.get(i)));
            }
            else if (action.get(i).equals("andi"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i)) & Integer.parseInt(inRegister2.get(i)));
            }
            else if (action.get(i).equals("ori"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i)) | Integer.parseInt(inRegister2.get(i)));
            }
            else if (action.get(i).equals("sll"))
            {
                Reg.put(outRegister.get(i),Reg.get(inRegister.get(i)) << Integer.parseInt(inRegister2.get(i)));
            }
            else if (action.get(i).equals("slt"))
            {   if(Reg.get(inRegister.get(i)) < Reg.get(inRegister2.get(i)))
                Reg.put(outRegister.get(i),1);
            else
                Reg.put(outRegister.get(i),0);
            }
            else if (action.get(i).equals("slti"))
            {   if(Reg.get(inRegister.get(i)) < Integer.parseInt(inRegister2.get(i)))
                Reg.put(outRegister.get(i),1);
            else
                Reg.put(outRegister.get(i),0);
            }
            else if (action.get(i).equals("lui"))
            {
                Reg.put(outRegister.get(i), Integer.parseInt(inRegister.get(i)) << 16);
            }
            else if (action.get(i).equals("beq"))
            {   if(!checkLabel(inRegister2.get(i)))
                return;
                if(Reg.get(outRegister.get(i))==Reg.get(inRegister.get(i)))
                {
                    i=labels.get(inRegister2.get(i)+':')-1;
                }
            }
            else if (action.get(i).equals("bne"))
            {   if(!checkLabel(inRegister2.get(i)))
                return;
                if(Reg.get(outRegister.get(i))!=Reg.get(inRegister.get(i)))
                {
                    i=labels.get(inRegister2.get(i))-1;
                }
            }
            else if (action.get(i).equals("j"))
            {       if(!checkLabel(outRegister.get(i)))
                           return;
                i=labels.get(outRegister.get(i)+':')-1;
            }
            else if (action.get(i).equals("sw"))
            {
                String[] off=inRegister.get(i).split("\\(|\\)");
                int offset=Integer.parseInt(off[0])+Reg.get(off[1]);
                Memory.put(offset+1000,Integer.toString(Reg.get(outRegister.get(i))));
            }
            else if (action.get(i).equals("lw"))
            {
                String[] off=inRegister.get(i).split("\\(|\\)");
                int offset=Integer.parseInt(off[0])+Reg.get(off[1])-1000;
                Reg.put(outRegister.get(i),Integer.parseInt(Memory.get(offset)));
            }
            if(Mem.get(action.get(i)).type.equals("R"))
            {
                Memory.put(i,"000000"+Mem.get(inRegister.get(i)).opCode+Mem.get(inRegister2.get(i)).opCode+"00000"+
                        Mem.get(action.get(i)).opCode);
            }
            if(Mem.get(action.get(i)).type.equals("I"))
            {
                Memory.put(i,Mem.get(action.get(i)).opCode+Mem.get(inRegister.get(i)).opCode+Mem.get(outRegister.get(i)).opCode
                                +Bin(inRegister2.get(i)));
            }
            if(Mem.get(action.get(i)).type.equals("J"))
            {
                Memory.put(i,Mem.get(action.get(i)).opCode+Bin(inRegister2.get(i)));
            }
        }
        System.out.println(Reg.get("$T1")+"&&"+Reg.get("$S2"));
    }
}