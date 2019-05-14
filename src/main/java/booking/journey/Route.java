package booking.journey;

import booking.exception.IllegalDirectionException;
import booking.exception.NoSuchStationException;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Route {
    private Map<String, Integer> stop;
    private String begin;
    private String end;

    public Route(List<String> station, List<Integer> distance) {
        this.stop = new LinkedHashMap<>();
        this.begin = station.get(0);
        this.end = station.get(station.size() - 1);
        for (int i = 0; i < station.size(); i++) {
            this.stop.put(station.get(i), distance.get(i));
        }
    }

    public Map<String, Integer> getStop() {
        return stop;
    }

    public void setStop(Map<String, Integer> stop) {
        this.stop = stop;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getDistance(String begin) throws NoSuchStationException, NullPointerException {
        if (begin == null || end == null) {
            throw new NullPointerException();
        }
        if (!stop.containsKey(begin)) {
            throw new NoSuchStationException();
        }

        return stop.get(begin);
    }

    public Integer getDistance(String begin, String end) throws NoSuchStationException, IllegalDirectionException,
            NullPointerException {
        if (begin == null || end == null) {
            throw new NullPointerException();
        }
        if ((!stop.containsKey(begin) || !stop.containsKey(end))) {
            throw new NoSuchStationException();
        }
        if (stop.get(begin) - stop.get(end) < 0) {
            throw new IllegalDirectionException();
        }

        return stop.get(begin) - stop.get(end);
    }

    public int getStationId(String station) {
        int id = 0;
        for (String stop : stop.keySet()) {
            if (station.equals(stop)) {
                return id;
            }

            id++;
        }
        return -1;
    }

    public boolean hasStation(String station) {
        return this.stop.containsKey(station);
    }

    public Map<String, Integer> getStops() {
        return stop;
    }
    //deprecated
//    public boolean isEnd(String station){
//        return stop.get(station) == stop.size() - 1;
//    }

    @Override
    public String toString() {
        return begin + " - " + end;
    }
}
