package com.toyota.terminalservice.service.abstracts;

import com.toyota.terminalservice.dto.TerminalDTO;
import org.springframework.data.domain.Page;

/**
 * Interface for TerminalServiceImpl
 */
public interface TerminalService {
    /**
     * Lists Terminals with paging, sorting and filtering.
     * @param page Page to be displayed
     * @param size Size of the page
     * @param name Filter for the field name.
     * @param isActive Filter for the field isActive
     * @param sortBy Which field to sort by
     * @param sortDirection Sort Direction (ASC/DESC)
     * @return Page of terminals
     */
    Page<TerminalDTO> getActiveTerminals(int page,int size,String name,boolean isActive,
                                         String sortBy,String sortDirection);

    /**
     * Creates terminal
     * @param terminalDTO Terminal to be created in database.
     */
    void createTerminal(TerminalDTO terminalDTO);

    /**
     * Activates Terminal
     * @param id ID of terminal to be activated.
     */
    void activateTerminal(Long id);

    /**
     * Disables Terminal
     * @param id ID of terminal to be disabled
     */
    void disableTerminal(Long id);

    /**
     * Soft deletes Terminal
     * @param id ID of terminal to be softly deleted.
     */
    void delete(Long id);
}
