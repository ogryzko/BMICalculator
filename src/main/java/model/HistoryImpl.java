package model;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by eglushchenko on 15.08.2017.
 */
public class HistoryImpl implements History {

    Optional<List<HistoryItem>> items = Optional.empty();

    @Override
    public void push(HistoryItem item) {
        if(!items.isPresent()){
            this.items = Optional.of(new ArrayList<HistoryItem>());
        }
        this.items.get().add(item);
    }

    @Override
    public Optional<List<HistoryItem>> getAll() {
        return items;
    }
}
