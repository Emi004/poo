import java.util.*;

interface Whiskey
{
    public double getConcentratieAlcool();
    public double getNrCalorii(double mili);
    public String toString();

}

class ClassicWhiskey implements Whiskey
{
    private String name;
    private double concentratie;
    

    public double getConcentratieAlcool()
    {
        return this.concentratie;
    }

    public ClassicWhiskey(String name, double concentratie)
    {
        this.name=name;
        this.concentratie=concentratie;
    }

    public double getNrCalorii(double mili)
    {
        return concentratie*mili*5;
    }

    public String toString()
    {
        return name+", Concentratie alcool: "+concentratie+"%, "+"Calorii pe 100ml: "+this.getNrCalorii(100)+" kcal";

    }
}

class JackAndHoney implements Whiskey
{
    private String name;
    private double concentratie;
    private int cantitateIndulcitor;

    public JackAndHoney(String name, double concentratie,int cantitateIndulcitor)
    {
        this.name=name;
        this.concentratie=concentratie;
        this.cantitateIndulcitor=cantitateIndulcitor;
    }
        public double getNrCalorii(double mili)
        {
            return concentratie*mili*5 + cantitateIndulcitor*mili*15;
        }

        public double getConcentratieAlcool()
        {
            return concentratie;
        }

        public String toString()
        {
            return name+", Concentratie alcool: "+concentratie+"%, "+"Calorii pe 100ml: "+this.getNrCalorii(100)+" kcal"+", Cantitate indulcitor: "+this.cantitateIndulcitor+" g";  
        }
    

}

class BlendedWhiskey implements Whiskey
{
    private String name;
    private ArrayList<Whiskey> whiskeys=new ArrayList<Whiskey>();

    public BlendedWhiskey(String name)
    {
        this.name=name;
    }
    public void adaugaBauturi(Whiskey w)
    {
        whiskeys.add(w);
    }

    public double getConcentratieAlcool()
    {
        double sum=0;
        for(Whiskey w:whiskeys)
        {
            sum=sum+ w.getConcentratieAlcool();
        }
        return sum/whiskeys.size();
    }

    public double getNrCalorii(double mili)
    {
        double sum=0;
        for(Whiskey w:whiskeys)
        {
            sum=sum+w.getNrCalorii(mili/whiskeys.size());

        }
        return sum;
    }
    public String toString()
    {
        String st="Nume: "+name +", concentratie: "+this.getConcentratieAlcool()+" %, Calorii pe 100ml: "+this.getNrCalorii(100)+" kcal, Compozitie: ";
        for(Whiskey w:whiskeys)
        {
            st=st+w.toString()+" | ";
        }
        return st;


    }
}

class Main
{
    public static void main(String args[])
    {
        ClassicWhiskey cw=new ClassicWhiskey("W1",30);
        ClassicWhiskey cw2=new ClassicWhiskey("W2",30);
        ClassicWhiskey cw3=new ClassicWhiskey("W3",30);
        JackAndHoney jh=new JackAndHoney("JH",45,23);
        BlendedWhiskey bw=new BlendedWhiskey("BW");
        BlendedWhiskey bw2=new BlendedWhiskey("BW2");
        bw.adaugaBauturi(cw2);
        bw.adaugaBauturi(cw3);
        bw2.adaugaBauturi(cw);
        bw2.adaugaBauturi(jh);
        bw2.adaugaBauturi(bw);
        System.out.println(bw2.toString());
        System.out.println(bw2.getNrCalorii(150));
    }

}