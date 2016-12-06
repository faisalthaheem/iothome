package com.computedsynergy.iot.home.services.brain.models.interfaces;

import com.computedsynergy.iot.home.services.brain.models.pojos.MqttMon;
import java.util.List;
import org.joda.time.DateTime;


/**
 *
 * @author Faisal Thaheem
 */

public interface MqttMonitorModel {

    int addEntry(MqttMon mon);
    List<MqttMon> listEntriesForDate(DateTime dated);
}
