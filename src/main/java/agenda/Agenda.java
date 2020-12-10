package agenda;

import java.time.LocalDate;
import java.util.*;
import agenda.Event;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
/**
 * Description : An agenda that stores events
 */
public class Agenda {
    
    List<Event> listeEvents;
    public Agenda(){
        listeEvents = new LinkedList();
    }
    /**
     * Adds an event to this agenda
     *
     * @param e the event to add
     */
    public void addEvent(Event e) {
        listeEvents.add(e);        
    }

    /**
     * Computes the events that occur on a given day
     *
     * @param day the day toi test
     * @return and iteraror to the events that occur on that day
     */
    
    public List<Event> eventsInDay(LocalDate day) {
        List<Event> eventsOfTheDay = new LinkedList();
        for (int i=0; i<listeEvents.size();i++){            
            if (listeEvents.get(i).isInDay(day)){
                eventsOfTheDay.add(listeEvents.get(i));
            }
          /*LocalDate debut = listeEvents.get(i).getStart().toLocalDate();
          LocalDate fin = debut.plus(listeEvents.get(i).getDuration());
          if(day.equals(debut) || (day.isAfter(debut) && day.isBefore(fin))){
              eventsOfTheDay.add(listeEvents.get(i));
          }*/
        }
        return eventsOfTheDay;
    }
    
        /**
     * Trouver les événements de l'agenda en fonction de leur titre
     * @param title le titre à rechercher
     * @return les événements qui ont le même titre
     */
    public List<Event> findByTitle(String title) {
        List<Event> eventsWithThisTitle = new LinkedList();
        for(Event e : listeEvents){
            if(e.getTitle().equals(title)){
                eventsWithThisTitle.add(e);
            }
        }
        return eventsWithThisTitle;
    }
    
    /**
     * Déterminer s’il y a de la place dans l'agenda pour un événement
     * @param e L'événement à tester (on se limitera aux événements simples)
     * @return vrai s’il y a de la place dans l'agenda pour cet événement
     */
    public boolean isFreeFor(Event e) {
        LocalDateTime end = e.getStart().plus(e.getDuration());
        LocalDateTime start = e.getStart();
        for(Event eventInList : listeEvents){
            LocalDateTime endInList = eventInList.getStart().plus(eventInList.getDuration());
            //Si le début OU la fin de "e" est entre le début ET la fin d'un événement dans la liste, alors ça se chevauche
            return !((start.isAfter(eventInList.getStart()) && start.isBefore(endInList)) || (end.isAfter(eventInList.getStart())&& end.isBefore(endInList)));
        }
        throw new UnsupportedOperationException("Pas encore implémenté");        
    }
}
