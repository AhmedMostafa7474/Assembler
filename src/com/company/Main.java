package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {
   static class Instraction
    {
        String name;
        String opCode;
        String type;
    }
    static HashMap<String,Instraction> Mem=new HashMap<>();
    static HashMap<String, Integer> Reg = new HashMap<>();
    static HashMap<String, Integer> labels = new HashMap<>();
    static HashMap<Integer, String> Memory = new HashMap<>();
    static ArrayList<String> action = new ArrayList<String>();
    static ArrayList<String> outRegister = new ArrayList<String>();
    static HashMap<Integer, String> inRegister = new HashMap<>();
    static HashMap<Integer, String> inRegister2 = new HashMap<>();
    static ArrayList<String> Command_by3par = new ArrayList<String>();
    static ArrayList<String> Command_by2par = new ArrayList<String>();
    static ArrayList<String> Command_by1par = new ArrayList<String>();
    public static void main(String[] args) throws IOException {
        BufferedReader D = new BufferedReader(new FileReader("Data.txt"));
        String y;
        while ((y = D.readLine()) != null) {
            String[] token = y.split(" ");
            Instraction P=new Instraction();
            if(token.length==3)
            {
                P.name=token[0];
                P.opCode=token[1];
                P.type=token[2];
            }
            if(token.length==2)
            {
                P.name=token[0];
                P.opCode=token[1];
            }
            Mem.put(P.name,P);
        }

        Command_by3par.add("add");
        Command_by3par.add("addi");
        Command_by3par.add("sub");
        Command_by3par.add("and");
        Command_by3par.add("or");
        Command_by3par.add("ori");
        Command_by3par.add("sll");
        Command_by3par.add("slt");
        Command_by3par.add("slti");
        Command_by3par.add("beq");
        Command_by3par.add("bne");

        Command_by2par.add("sw");
        Command_by2par.add("lw");

        Command_by1par.add("j");
        Command_by1par.add("jr");
        Command_by1par.add("lui");
        Reg.put("$S0",0);
        Reg.put("$S1",0);
        Reg.put("$S2",0);
        Reg.put("$S3",0);
        Reg.put("$S4",0);
        Reg.put("$S5",0);
        Reg.put("$T1",0);
        Reg.put("$T2",0);
        Reg.put("$T3",0);
        Reg.put("$T4",0);
        String x;
        Parser P =new Parser();
         /*Scanner input = new Scanner(System.in);
            String x = input.nextLine();
            String lines[] = x.split("\\r?\\n");
            for(int i=0 ;i<lines.length ;i++)                   //INPUT Done By GUI
            {
                String[] arrCommands = lines[i].split("\\s|,");
                P.Parser1(arrCommands);
            }*/
        BufferedReader br = new BufferedReader(new FileReader("Text.txt"));
        while ((x = br.readLine()) != null) {
            String[] arrCommands = x.split("\\s|,");
            //Delete When Use GUI

            boolean Y= P.Parser1(arrCommands);
            if(Y==false)
            {
                return;
            }
        }
        Assembler A=new Assembler();
        Input();
        A.Assempler();
    }
    static void Input()
    {
        Reg.put("$S0",6);
        Reg.put("$S1",11);              //Registers Input Done By GUI
        Reg.put("$S2",7);
    }

    //Output is Hashmap Reg and Memory
}
