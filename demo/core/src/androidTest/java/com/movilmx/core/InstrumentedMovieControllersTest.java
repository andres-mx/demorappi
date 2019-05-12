package com.movilmx.core;

import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.movilmx.core.communication.MovieControllerNotifier;
import com.movilmx.core.controllers.MovieControllers;
import com.movilmx.core.videos.Container;

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
        movieControllers = MovieControllers.newInstance(InstrumentationRegistry.getContext());
    }

    /**
     * Prueba para un caso exitoso del servicio de las película top
     * @throws Exception: Excepción en caso de error
     */
    @Test
    public void getTopRated() throws Exception {
        final Object topRated = new Object();
        movieControllers.requestMovies("1", (eventType, movieControllerObject) -> {
            assertNotNull(eventType);
            assertEquals(MovieControllerNotifier.MovieControllerEventType.TOPRATED,eventType);
            assertNotNull(movieControllerObject);
            assertNotNull(movieControllerObject.getData());
            assertThat(movieControllerObject.getData(),instanceOf(Container.class));
            assertNotNull(((Container)movieControllerObject.getData()).getVideos());
            synchronized (topRated){
                topRated.notify();
            }
        });

        synchronized (topRated){
            topRated.wait();
        }
    }

    /**
     * Prueba para un caso exitoso del servicio de películas a estrenar
     * @throws Exception
     */
    @Test
    public void getUpComing() throws Exception {
        final Object upComing = new Object();
        movieControllers.requestUpComing("1", (eventType, movieControllerObject) -> {
            assertNotNull(eventType);
            assertEquals(MovieControllerNotifier.MovieControllerEventType.UPCOMING,eventType);
            assertNotNull(movieControllerObject);
            assertNotNull(movieControllerObject.getData());
            assertThat(movieControllerObject.getData(),instanceOf(Container.class));
            assertNotNull(((Container)movieControllerObject.getData()).getVideos());
            synchronized (upComing){
                upComing.notify();
            }
        });

        synchronized (upComing){
            upComing.wait();
        }
    }

    /**
     * Prueba para un caso exitoso del servicio de películas más populares
     * @throws Exception
     */
    @Test
    public void getPopular() throws Exception {
        final Object popular = new Object();
        movieControllers.requestPopular("1", (eventType, movieControllerObject) -> {
            assertNotNull(eventType);
            assertEquals(MovieControllerNotifier.MovieControllerEventType.POPULAR,eventType);
            assertNotNull(movieControllerObject);
            assertNotNull(movieControllerObject.getData());
            assertThat(movieControllerObject.getData(),instanceOf(Container.class));
            assertNotNull(((Container)movieControllerObject.getData()).getVideos());
            synchronized (popular){
                popular.notify();
            }
        });

        synchronized (popular){
            popular.wait();
        }
    }
}