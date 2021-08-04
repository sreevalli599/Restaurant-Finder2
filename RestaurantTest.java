9import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    @mock
    Restaurant restaurant;
    
    //REFACTOR ALL THE REPEATED LINES OF CODE
@BeforeEach
public void setup(){
 LocalTime openingTime = LocalTime.parse("10:30:00");
 LocalTime closingTime = LocalTime.parse("22:00:00");
 restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
 restaurant.addToMenu("Biryani",130);
        restaurant.addToMenu("Vegetable fried rice", 230);
}
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time()
       Restaurant spiTime = Mockito.spy(restaurant);
        Mockito.when(spiTime.getCurrentTime()).thenReturn(CorrectTime); if(restaurant.getCurrentTime().after(openingTime)&&restaurant.getCurrentTime().after(closingTime))
        assertTrue(spiTime.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
   Restaurant spiTime = Mockito.spy(restaurant);
        Mockito.when(spiTime.getCurrentTime()).thenReturn(CorrectTime);     if(restaurant.getCurrentTime().after(openingTime)&&restaurant.getCurrentTime().before(closingTime))
     assertFalse(spiTime.isRestaurantOpen());    

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
      @Test
    public void total_cost_when_the_total_cost_of_items_added(){

        List<Item> items = new ArrayList<>();

        items.add(new Item("Biryani",130));
        items.add(new Item("Vegetable fried rice", 230));
        int total = restaurant.calculateTotalCost(items);
        assertEquals(360,total);

    }

    @Test
    public void total_cost_returning_0_if_no_items_added(){
        List<Item> items = new ArrayList<>();
        int total = restaurant.calculateTotalCost(items);
        assertEquals(0,total);
    }
}
