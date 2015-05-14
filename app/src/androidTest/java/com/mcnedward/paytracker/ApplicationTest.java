package com.mcnedward.paytracker;

import android.app.Application;
import android.test.ApplicationTestCase;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    @Test
    public void testPayByHour() {
        int pay = Helper.calculatePayByHour(9, 8);
        assertThat(pay, not(0));
        assertThat(pay, is(72));
    }

    @Test
    public void testPaySoFar() {
        int pay = Helper.calculatePaySoFar(9, 18);
        assertThat(pay, not(0));
        assertThat(pay, is(9));
    }
}