package com.computedsynergy.iot.home.services.brain.models.interfaces;

import com.computedsynergy.iot.home.services.brain.models.pojos.MqttMon;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Faisal Thaheem
 */

public interface MqttMonitorModel {

    int addEntry(MqttMon mon);
    List<MqttMon> listEntriesForDate(Date dated);
}
