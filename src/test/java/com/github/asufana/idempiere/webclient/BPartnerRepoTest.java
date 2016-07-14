package com.github.asufana.idempiere.webclient;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import com.github.asufana.idempiere.webclient.functions.WSResponse;

public class BPartnerRepoTest {
    
    @Test
    //Create
    public void testCreate() throws Exception {
        final WSResponse response = create();
        
        System.out.println(response);
    }
    
    private WSResponse create() {
        final String name = "WebClientTest:" + System.currentTimeMillis();
        final WSResponse response = BPartnerRepo.create(name, name);
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        return response;
    }
    
    @Test
    //Query
    public void testFindByName() throws Exception {
        final WSResponse response = BPartnerRepo.findByName("GardenAdmin BP");
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Read
    public void testFindById() throws Exception {
        final WSResponse response = BPartnerRepo.findById(113); //GardenAdmin BP
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Update
    public void testUpdate() throws Exception {
        //Create
        final WSResponse createResponse = create();
        
        //Update
        final WSResponse response = BPartnerRepo.update(createResponse.recordId(),
                                                        "WebClientTestUpdated:"
                                                                + System.currentTimeMillis());
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
    @Test
    //Delete
    public void testDelete() throws Exception {
        //Create
        final WSResponse createResponse = create();
        
        //Delete
        final WSResponse response = BPartnerRepo.delete(createResponse.recordId());
        
        assertThat(response.isSuccess(), is(true));
        assertThat(response.size(), is(not(0)));
        System.out.println(response);
    }
    
}
