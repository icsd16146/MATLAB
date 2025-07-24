package ai;

public class Person {

    int time, pro;
    

    Person(int time, int pro) {
        this.time = time;
        this.pro = pro;
    }

    public int getPro() {
        return pro;
    }
    
    @Override
   public String toString(){
       return "A"+pro;
   }

}
