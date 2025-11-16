package com.example.m03_bounce;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestActivity {

    DBClass testDb1;
    DBClass testDb2;
    DBClass testDb3;

    @Before
    public void setUp() {
        Context ctx = InstrumentationRegistry.getInstrumentation().getTargetContext();

        testDb1 = new DBClass(ctx, "test1");
        testDb2 = new DBClass(ctx, "test2");
        testDb3 = new DBClass(ctx, "test3");

        testDb1.clearAll();
        testDb2.clearAll();
        testDb3.clearAll();

        testDb1.save(new DataModel(100f, 200f, 5f, 5f, -65536, "ball1"));
        testDb1.save(new DataModel(300f, 400f, -3f, 4f, -16711681, "ball2"));
        testDb1.save(new DataModel(200f, 400f, -3f, 4f, -16711671, "ball3"));

        testDb2.save(new DataModel(100f, 200f, 5f, 5f, -65536, "ball4"));
        testDb2.save(new DataModel(300f, 400f, -3f, 4f, -16711681, "ball5"));
        testDb2.save(new DataModel(200f, 400f, -3f, 4f, -16711671, "ball6"));

        testDb3.save(new DataModel(100f, 200f, 5f, 5f, -65536, "ball7"));
        testDb3.save(new DataModel(300f, 400f, -3f, 4f, -16711681, "ball8"));
        testDb3.save(new DataModel(200f, 400f, -3f, 4f, -16711671, "ball9"));
    }

    @Test
    public void test1() {
        List<DataModel> balls1 = testDb1.findAll();

        assertEquals(3, balls1.size());

        DataModel t1 = balls1.get(0);
        assertEquals(-65536, t1.getColor());
        assertEquals("ball1", t1.getName());
    }

    @Test
    public void test2() {
        List<DataModel> balls2 = testDb2.findAll();

        assertEquals(3, balls2.size());

        DataModel t2 = balls2.get(1);
        assertEquals(300f, t2.getModelX(), 0.0f);
        assertEquals(-3f, t2.getModelDX(), 0.0f);
        assertEquals("ball5", t2.getName());
    }

    @Test
    public void test3() {
        List<DataModel> balls3 = testDb3.findAll();

        assertEquals(3, balls3.size());

        DataModel t3 = balls3.get(2);
        assertEquals(400f, t3.getModelY(), 0.0f);
        assertEquals(4f, t3.getModelDY(), 0.0f);
        assertEquals("ball9", t3.getName());
    }

    @Test
    public void deleteByIdTest_03() {
        testDb1.deleteById(3L);
        List<DataModel> ALL = testDb1.findAll();
        for (DataModel one : ALL) {
            Log.w("AndroidTest", "Setup()=> Item => " + one.toString());
        }
    }


}
