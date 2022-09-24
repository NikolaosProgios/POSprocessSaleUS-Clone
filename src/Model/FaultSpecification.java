/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author alefrag
 *
 * Η κλάση αναπαριστά την αιτιολογία για μια επιστροφή προϊόντος
 */
public class FaultSpecification {

    private int code;
    private String description;

    public FaultSpecification(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * Get the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the value of description
     *
     * @param description new value of description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the value of code
     *
     * @return the value of code
     */
    public int getCode() {
        return code;
    }

    /**
     * Set the value of code
     *
     * @param code new value of code
     */
    public void setCode(int code) {
        this.code = code;
    }

}
