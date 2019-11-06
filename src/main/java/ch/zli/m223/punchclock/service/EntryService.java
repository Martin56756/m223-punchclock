package ch.zli.m223.punchclock.service;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.EntryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryService {
    private EntryRepository entryRepository;

    public EntryService(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry createEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }

    public List<Entry> findAll() {
        return entryRepository.findAll();
    }

    public List<Entry> findByUser(String userName) {
        return entryRepository.findByUser(userName);
    }

    public void deleteEntry(long entryId) {
        entryRepository.deleteById(entryId);
    }

    public Entry updateEntry(Entry entry) {
        return entryRepository.saveAndFlush(entry);
    }
}
