package com.example.covidtracker;

public class Model {
    private String sname,dname;
    private long total, death, cured, active, incAct, incDea, incRec;

    public Model(String sname,String dname, long total, long death, long cured,
                 long active, long incAct, long incDea, long incRec)
    {
        this.sname = sname;
        this.dname = dname;
        this.total = total;
        this.death = death;
        this.cured = cured;
        this.active = active;
        this.incAct = incAct;
        this.incDea = incDea;
        this.incRec = incRec;
    }

    public long getIncAct()
    {
        return incAct;
    }

    public void setIncAct(long incAct)
    {
        this.incAct = incAct;
    }

    public long getIncDea()
    {
        return incDea;
    }

    public void setIncDea(long incDea)
    {
        this.incDea = incDea;
    }

    public long getIncRec()
    {
        return incRec;
    }

    public void setIncRec(long incRec)
    {
        this.incRec = incRec;
    }

    public String getSname()
    {
        return sname;
    }

    public void setSname(String sname)
    {
        this.sname=sname;
    }

    public String getDname()
    {
        return dname;
    }

    public void setDname(String dname)
    {
        this.dname= dname;
    }
    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public long getDeath()
    {
        return death;
    }

    public void setDeath(long death)
    {
        this.death = death;
    }

    public long getCured()
    {
        return cured;
    }

    public void setCured(long cured)
    {
        this.cured = cured;
    }

    public long getActive()
    {
        return active;
    }

    public void setActive(long active)
    {
        this.active = active;
    }
}