function formatDateToDDMMYYYY(dateInput) {
        const date = new Date(dateInput);
        return new Intl.DateTimeFormat('en-GB', { 
            day: '2-digit', 
            month: '2-digit', 
            year: 'numeric' 
        }).format(date);
    }
 