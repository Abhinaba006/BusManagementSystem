package nrifintech.busMangementSystem.Service.impl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import nrifintech.busMangementSystem.repositories.RouteMapRepo;

@RunWith(MockitoJUnitRunner.class)
public class RouteMapServiceImplTest {
    
    @Mock
    private RouteMapRepo routeMapRepo;
    
    @InjectMocks
    private RouteMapServiceImpl routeMapServiceImpl;
    
    @Test
    public void testAddRouteMap() {
        // Test empty implementation
        routeMapServiceImpl.addRouteMap(1, 2, 3, "10:00");
        // Verify that the repository method is not called
        verify(routeMapRepo, never()).save(any());
    }
}
