package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private EntryService entryService;
    private UserRepository userService;

    public EntryController(EntryService entryService, UserRepository userService) {
        this.entryService = entryService;
        this.userService = userService;
    }

    /**
     * Gets all entries from the database
     * @return All entries in the database
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    /**
     * Gets all entries of the specified user
     * @param userName The username to filter by
     * @return All entries that belong to the specified user
     */
    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getEntriesOfUser(@PathVariable("userName") String userName) {
        return entryService.findByUser(userName);
    }

    /**
     * Adds a new entry to the database
     * @param entry The entry to add
     * @return The added entry
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    /**
     * Deletes the entry with the specified entry id
     * @param entryId The ID of the entry to delete
     */
    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable("entryId") long entryId) {
        entryService.deleteEntry(entryId);
    }

    /**
     * Updates the specified entry in the database
     * @param entry The entry to update
     * @return The updated entry
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@Valid @RequestBody Entry entry) {
        return entryService.updateEntry(entry);
    }
}
