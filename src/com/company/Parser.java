package com.company;

public class Parser extends Main {

    static boolean Label(String[] arrCommands) {
        if (arrCommands[0].contains(":"))
            return true;

        return false;
    }

    static boolean Parser1(String[] arrCommands) {
        int k = 0;
        int choice = 0;
        while (true) {
            for (int i = 0; i < Command_by1par.size(); i++)
                if (Command_by1par.get(i).equals(arrCommands[k])) {
                    choice = 1;
                    break;
                }
            for (int i = 0; i < Command_by2par.size(); i++)
                if (Command_by2par.get(i).equals(arrCommands[k])) {
                    choice = 2;
                    break;
                }

            for (int i = 0; i < Command_by3par.size(); i++)
                if (Command_by3par.get(i).equals(arrCommands[k])) {
                    choice = 3;
                    break;
                }
            if (choice == 0) {
                if (arrCommands[k].contains(":")) {
                    k = 1;
                } else {
                    System.out.println(" Wrong Syntax");
                    return false;
                }
            } else {
                break;
            }

        }

        if (choice == 1 && (arrCommands.length == 2 || (arrCommands.length == 3 && arrCommands[0].contains(":")))) {
            if (arrCommands[k] == "j") {
                if (!labels.containsKey(arrCommands[k + 3]))
                    System.out.println(arrCommands[k] + " Wrong Label");
                return false;
            }
            action.add(arrCommands[k]);
            outRegister.add(arrCommands[k + 1]);
            if (k != 0) {
                labels.put(arrCommands[0], action.size() - 1);
            }
            return true;
        } else if (choice == 1) {
            System.out.println(arrCommands[k] + " Wrong Syntax");
            return false;
        }
        if (choice == 2 && ((arrCommands.length == 3 && !Label(arrCommands)) || (arrCommands.length == 4 ))) {
            action.add(arrCommands[k]);
            outRegister.add(arrCommands[k + 1]);
            inRegister.put(action.size() - 1, arrCommands[k + 2]);
            if (k != 0) {
                labels.put(arrCommands[0], action.size() - 1);
            }
            return true;
        } else if (choice == 2) {
            System.out.println(arrCommands[k] + " Wrong Syntax");
            return false;
        }
        if (choice == 3 && ((arrCommands.length == 4 && !Label(arrCommands)) || (arrCommands.length == 5 && Label(arrCommands))) ) {
            action.add(arrCommands[k]);
            outRegister.add(arrCommands[k + 1]);
            inRegister.put(action.size() - 1, arrCommands[k + 2]);
            inRegister2.put(action.size() - 1, arrCommands[k + 3]);
            if (k != 0) {
                labels.put(arrCommands[0], action.size() - 1);
            }
            return true;
        } else if (choice == 3) {
            System.out.println(arrCommands[k] + "  Wrong Syntax");
            return false;
        }
        return false;

    }
}
