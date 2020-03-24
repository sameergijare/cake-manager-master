package com.waracle.cakemgr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CakeDaoTest {

    private CakeDao testInstance;

    @Before
    public void setup() {
        testInstance = new CakeDao();
    }

    @After
    public void tearDown() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testGetCakesWhenDBEmpty() {
        List<CakeEntity> cakes = testInstance.getCakes();

        assertTrue(cakes != null);
        assertEquals(0, cakes.size());
    }

    @Test
    public void testAddCake() {
        List<CakeEntity> cakes = testInstance.getCakes();
        assertTrue(cakes != null);
        assertEquals(0, cakes.size());

        CakeEntity chocCake = new CakeEntity();
        chocCake.setTitle("Chocolate cake");
        chocCake.setDescription("A cake made from chocolate");
        chocCake.setImageUrl("http://choccake.url");

        testInstance.addCake(chocCake);
        cakes = testInstance.getCakes();

        assertTrue(cakes != null);
        assertEquals(1, cakes.size());
        assertEquals(1, cakes.get(0).getId());
        assertEquals("Chocolate cake", cakes.get(0).getTitle());
        assertEquals("A cake made from chocolate", cakes.get(0).getDescription());
        assertEquals("http://choccake.url", cakes.get(0).getImageUrl());
    }
}