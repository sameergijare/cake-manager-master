package com.waracle.cakemgr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class CakeServiceTest {

    private CakeService testInstance;

    @Before
    public void setup() {
        testInstance = new CakeService();
    }

    @After
    public void tearDown() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testInitalCakesAreAsExpected() throws Exception {
        Set<CakeEntity> initialCakes = testInstance.getInitialCakes();

        assertTrue(initialCakes != null);
        assertEquals(5, initialCakes.size());

        List<CakeEntity> initialCakesAsList = new ArrayList<>(initialCakes);

        assertEquals(null, initialCakesAsList.get(0).getId());
        assertEquals("Lemon cheesecake", initialCakesAsList.get(0).getTitle());

        assertEquals(null, initialCakesAsList.get(1).getId());
        assertEquals("victoria sponge", initialCakesAsList.get(1).getTitle());

        assertEquals(null, initialCakesAsList.get(2).getId());
        assertEquals("Carrot cake", initialCakesAsList.get(2).getTitle());

        assertEquals(null, initialCakesAsList.get(3).getId());
        assertEquals("Birthday cake", initialCakesAsList.get(3).getTitle());

        assertEquals(null, initialCakesAsList.get(4).getId());
        assertEquals("Banana cake", initialCakesAsList.get(4).getTitle());
    }

    @Test
    public void testGetAllCakes() throws Exception {
        List<CakeEntity> allCakes = testInstance.getAllCakes();

        assertTrue(allCakes != null);
        assertEquals(0, allCakes.size());
    }

    @Test
    public void testAddCake() throws Exception {
        CakeEntity newCake = new CakeEntity();
        newCake.setTitle("Chocolate cake");
        newCake.setDescription("A cake made from chocolate");
        newCake.setImageUrl("http://choccake.url");

        testInstance.saveCake(newCake);

        List<CakeEntity> allCakes = testInstance.getAllCakes();

        assertTrue(allCakes != null);
        assertEquals(1, allCakes.size());

        assertEquals(1, allCakes.get(0).getId());
        assertEquals("Chocolate cake", allCakes.get(0).getTitle());
    }

    @Test
    public void testAddMultipleCakes() throws Exception {
        List<CakeEntity> allCakes = testInstance.getAllCakes();

        assertTrue(allCakes != null);
        assertEquals(0, allCakes.size());

        CakeEntity newCake = new CakeEntity();
        newCake.setTitle("Test Title");
        newCake.setDescription("Test Description");
        newCake.setImageUrl("Test Image Url");

        CakeEntity newCake2 = new CakeEntity();
        newCake2.setTitle("Test Title 2");
        newCake2.setDescription("Test Description 2");
        newCake2.setImageUrl("Test Image Url 2");

        testInstance.saveCakes(asList(newCake, newCake2));

        allCakes = testInstance.getAllCakes();

        assertTrue(allCakes != null);
        assertEquals(2, allCakes.size());

        assertEquals(1, allCakes.get(0).getId());
        assertEquals("Test Title", allCakes.get(0).getTitle());

        assertEquals(2, allCakes.get(1).getId());
        assertEquals("Test Title 2", allCakes.get(1).getTitle());
    }
}