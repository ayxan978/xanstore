package com.project.xanstore.model;

public class CounterModel {
    private int count;

    public CounterModel()
    {
        count = 0;
    }

    public CounterModel(int init)
    {
        count = init;
    }

    public int get()
    {
        return count;
    }

    public void clear()
    {
        count = 0;
    }

    public int incrementAndGet()
    {
        return ++count;
    }

    public String toString()
    {
        return ""+count;
    }
}
