package mops.gruppen1.domain;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representing the name of a user.
 * Will be received from KeyCloak Server and is unique.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class Username {
    @CsvBindByName
    private String username;

    @Override
    public String toString() {
        return username;
    }
}
