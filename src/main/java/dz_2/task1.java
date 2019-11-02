package dz_2;

import java.util.ArrayList;

public class task1 {
    public static void main(String[] args) {
        try{
            Object npe = null;
            npe.toString();
        }
        catch (Exception e){
            System.out.println("смоделирован NullPointerException:");
            e.printStackTrace();
        }
        try{
            ArrayList<String> emptyList = new ArrayList<String>();
            emptyList.get(0);
        }catch (Exception e){
            System.out.println("смоделирован ArrayIndexOutOfBoundsException:");
            e.printStackTrace();
        }
        try{
            throw new ClassCastException();
        }catch (Exception e){
            System.out.println("смоделирован бросок через throw:");
            e.printStackTrace();
        }
    }
}
