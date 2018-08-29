package com.arvipdev.arvind.ibmmasterchef.com.model.ibmmasterchef;

/**
 * Created by ARVIND on 8/23/2018.
 */

public class BaseCandidate
{
    private String name;
    private String emp_Id;

    public BaseCandidate(String name, String emp_Id)
    {
        this.name = name;
        this.emp_Id = emp_Id;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public void setEmp_Id (String emp_Id)
    {
        this.emp_Id = emp_Id;
    }

    public String getName ()
    {
        return this.name;
    }

    public String getEmp_Id ()
    {
        return this.emp_Id;
    }


}
