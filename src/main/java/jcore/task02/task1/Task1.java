package jcore.task02.task1;

import java.util.ArrayList;

public class Task1 {
    public static void main(String[] args) {
        try{
            Object npe = null;
            npe.toString();
        }
        catch (Exception e){
            printException(e, "смоделирован NullPointerException:");
        }
        try{
            ArrayList<String> emptyList = new ArrayList<String>();
            emptyList.get(0);
        }catch (Exception e){
            printException(e, "смоделирован ArrayIndexOutOfBoundsException:");
        }
        try{
            throw new ClassCastException();
        }catch (Exception e){
            printException(e, "смоделирован бросок через throw:");
        }
    }

    private static void printException(Exception e, String s) {
        System.out.println(s);
        e.printStackTrace();
    }
}
