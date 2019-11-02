package ch.zli.m223.punchclock.controller;

import ch.zli.m223.punchclock.domain.Entry;
import ch.zli.m223.punchclock.repository.UserRepository;
import ch.zli.m223.punchclock.service.EntryService;
import ch.zli.m223.punchclock.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getAllEntries() {
        return entryService.findAll();
    }

    @GetMapping("/{userName}")
    @ResponseStatus(HttpStatus.OK)
    public List<Entry> getEntriesOfUser(@PathVariable("userName") String userName) {
        var allEntries = entryService.findAll();
        var user = userService.findByUserName(userName);
        List<Entry> retList = new ArrayList<Entry>();
        for (int i = 0; i < allEntries.size(); i++) {
            if (allEntries.get(i).getUser().getUserName() == user.getUserName()) {
                retList.add(allEntries.get(i));
            }
        }
        return retList;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entry createEntry(@Valid @RequestBody Entry entry) {
        return entryService.createEntry(entry);
    }

    @DeleteMapping("/{entryId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteEntry(@PathVariable("entryId") long entryId) {
        entryService.deleteEntry(entryId);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Entry updateEntry(@Valid @RequestBody Entry entry) {
        return entryService.updateEntry(entry);
    }
}
