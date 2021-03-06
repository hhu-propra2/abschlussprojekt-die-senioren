package mops.gruppen1.applicationService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import mops.gruppen1.data.EventDTO;
import mops.gruppen1.data.IEventRepo;
import mops.gruppen1.domain.events.IEvent;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to handle and manage events.
 */
@Getter
@Component
@Service
public class EventService {

    final IEventRepo eventRepo;
    private final String eventClassPath = "mops.gruppen1.domain.events.";
    private List<IEvent> events;

    /**
     * Init EventService.
     *
     * @param eventRepo
     */
    public EventService(IEventRepo eventRepo) {
        this.eventRepo = eventRepo;
        events = new ArrayList<IEvent>();
    }

    /**
     * Load all events from the EventRepo.
     */
    public void loadEvents() {

        //Get all EventDTO´s from EventRepo
        Iterable<EventDTO> eventDTOS = eventRepo.findAll();
        //Fill list of events
        eventDTOS.forEach(e -> {
            IEvent event = transform(e);
            events.add(event);
        });
    }

    /**
     * Transformation of generic EventDTO to specifc EventType.
     *
     * @param eventDTO
     * @return Returns initialized Event
     */
    public IEvent transform(EventDTO eventDTO) {

        //Jackson ObjectMapper
        ObjectMapper objectMapper = new ObjectMapper();

        try {

            //Get specifc classType for eventDTO
            Class<IEvent> classType = (Class<IEvent>) Class.forName(eventClassPath + eventDTO.getEventType());

            //Deserialize Json-Payload
            IEvent event = objectMapper.readValue(eventDTO.getPayload(), classType);

            return event;

        } catch (JsonProcessingException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveToRepository(EventDTO eventDTO) {
        eventRepo.save(eventDTO);
    }


    /**
     * Method receives parameters and uses them to create an EventDTO.
     * @param userName
     * @param groupID
     * @param timestamp
     * @param eventType
     * @param event
     * @return EVentDTO with given parameters.
     */
    public EventDTO createEventDTO(String userName, String groupID, LocalDateTime timestamp,
                                   String eventType, IEvent event) {
        ObjectMapper objectMapper = new ObjectMapper();
        String payload = "";
        try {
            payload = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new EventDTO(userName, groupID, timestamp, eventType, payload);
    }


}
