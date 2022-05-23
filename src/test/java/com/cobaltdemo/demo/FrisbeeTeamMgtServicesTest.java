package com.cobaltdemo.demo;
import com.cobaltdemo.demo.model.Player;
import com.cobaltdemo.demo.services.FrisbeePlayersMgtServices;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class FrisbeeTeamMgtServicesTest {

    private static Player playerSampleOne;
    private static Player playerSampleTwo;
    private static Player playerSampleThree;
    private static Player updaterPlayerRecord;
    private static FrisbeePlayersMgtServices playerService;

    @Autowired
    private MockMvc mvc;

    @Autowired
    Player playertt;

    @BeforeClass
    public static void init() throws IOException {

        playerService = mock(FrisbeePlayersMgtServices.class);

        playerSampleOne = new Player(10022,"groum","Workalemhu","Forward");

        playerSampleTwo = new Player(10032,"Ronaldo","Christano","Cutter");

        playerSampleThree = new Player(10021,"Mesi","Leo","Cutter");

        updaterPlayerRecord = new Player(1007,"Michael","johnsen","Cutter");


        when(playerService.getAllPlayersdata()).thenReturn(Arrays.asList(playerSampleOne,playerSampleTwo,playerSampleThree));

        when(playerService.getPlayerById(10021)).thenReturn(playerSampleThree);

        when(playerService
                .updatePlayerINfo(1032,"Michael","johnsen","Defence"))
                .thenReturn(updaterPlayerRecord);
    }

    @Test
    public  void  getAllPlayersoldDataTest() throws Exception {
        List allPlayers = playerService.getAllPlayersdata();
        assertNotNull(allPlayers);
        assertEquals(3, allPlayers.size());

    }
    @Test
    public  void  getPlayerById() throws Exception {
        int employeeId = 10021;

        Player playerRecord = playerService.getPlayerById(employeeId);
        assertEquals("Mesi", playerRecord.getFirstName());

    }
    @Test
    public  void  updatePlayerById() throws Exception {
        Player updatedRecord = playerService.updatePlayerINfo(1032,"Michael","johnsen","Defence");
        assertEquals("Michael", updatedRecord.getFirstName());
        assertEquals("johnsen", updatedRecord.getLastName());
        assertEquals("Cutter", updatedRecord.getPosition());

    }


    public  void  getAllPlayersData() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .get("/getPlayersData")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

    }




}
