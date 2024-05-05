function formatDateToDDMMYYYY(dateInput) {
        const date = new Date(dateInput);
        return new Intl.DateTimeFormat('en-GB', { 
            day: '2-digit', 
            month: '2-digit', 
            year: 'numeric' 
        }).format(date);
    }
    
    function formatDateToDDMMMYYYY(dateInput) {
        const date = new Date(dateInput);
        return new Intl.DateTimeFormat('en-GB', {
            day: '2-digit',
            month: 'short',  // Changed from '2-digit' to 'short' to get month abbreviation
            year: 'numeric'
        }).format(date); // Ensure the formatting includes dashes
    }

    export { formatDateToDDMMYYYY, formatDateToDDMMMYYYY };
  