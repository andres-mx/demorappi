package com.movilmx.core;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.communication.MovieControllerObject;
import com.movilmx.core.controllers.MovieControllers;
import com.movilmx.networkcontroller.models.topRated.response.TopRated;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class InstrumentedMovieControllersTest {
    private MovieClient      movieClient;
    private MovieControllers movieControllers;

    @Before
    public void setUp() throws Exception {
        movieClient = MovieClient.newInstance(
                InstrumentationRegistry.getContext(),
                BuildConfig.THEMOVIE);
        movieControllers = MovieControllers.getInstance(InstrumentationRegistry.getContext());
    }

    @Test
    public void getTopRated() throws Exception {
        final Object topRated = new Object();
        movieControllers.requestMovies("1", (eventType, movieControllerObject) -> {
            assertNotNull(eventType);
            assertEquals(MovieControllerNotifier.MovieControllerEventType.TOPRATED,eventType);
            assertNotNull(movieControllerObject);
            assertNotNull(movieControllerObject.getData());
            assertThat(movieControllerObject.getData(),instanceOf(TopRated.class));
            assertNotNull(((TopRated)movieControllerObject.getData()).getResults());
            synchronized (topRated){
                topRated.notify();
            }
        });

        synchronized (topRated){
            topRated.wait();
        }
    }
}
