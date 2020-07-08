public class WaitAndNotifyTest{
    public static void main(String[] args) {
        Gene gene=new Gene();
        Read read=new Read();
        read.setGene(gene);
        new Thread(read).start();
        new Thread(gene).start();
    }

}

class Gene implements Runnable{
    private String oweString;

    public synchronized void gene(){
        StringBuilder sb=new StringBuilder();
        char s='a';
        while(s<='z'){
            sb.append(s);
            s++;
        }
        oweString=sb.toString();
        System.out.println(oweString.toString());
        notify();
    }

    public String getGene(){
        return oweString;
    }

    public void run(){
        gene();
    }
}
class Read implements Runnable{
    private String oweString;
    private Gene gene;
    
    public void setGene(Gene gene){
        this.gene=gene;
    }

    public synchronized void setOweString() throws InterruptedException{
        System.out.println("still wait...");
        wait();
        oweString=gene.getGene();
    }

    public String getOweString(){
        return oweString;
    }

    public void run(){
        try{
            setOweString();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(oweString);
    }
}