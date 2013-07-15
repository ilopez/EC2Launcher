/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ilopez.amazon.ec2launcher;


import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;

import java.util.ArrayList;


/**
 *
 * @author User
 */
public class EC2Launcher {
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UI ui = new UI();    
        ui.setVisible(true);               
    }
    
    public static String startOneInstance(String instance, String accesskey, String secretkey) throws Exception {
        AmazonEC2 ec2 = new com.amazonaws.services.ec2.AmazonEC2Client( new BasicAWSCredentials(accesskey, secretkey) );
        Region usWest1 = Region.getRegion(Regions.US_WEST_1);
        ec2.setRegion(usWest1);
        
        StartInstancesRequest sir = new StartInstancesRequest();
        
        ArrayList<String> instanceid = new ArrayList<>();
        instanceid.add(instance); // My Virtual Machine Box
        
        sir.setInstanceIds(instanceid);
        StartInstancesResult sirs = ec2.startInstances(sir);
        return sirs.getStartingInstances().get(0).getCurrentState().getName();
    }
    public static String stopOneInstance(String instance, String accesskey, String secretkey) throws Exception {
        AmazonEC2 ec2 = new com.amazonaws.services.ec2.AmazonEC2Client( new BasicAWSCredentials(accesskey, secretkey) );
        Region usWest1 = Region.getRegion(Regions.US_WEST_1);
        ec2.setRegion(usWest1);
        
        StopInstancesRequest sir = new StopInstancesRequest();
        
        ArrayList<String> instanceid = new ArrayList<>();
        instanceid.add(instance); // My Virtual Machine Box
        
        sir.setInstanceIds(instanceid);
        
        StopInstancesResult sirs = ec2.stopInstances(sir);
        return sirs.getStoppingInstances().get(0).getCurrentState().getName() ;
    }
    
}
