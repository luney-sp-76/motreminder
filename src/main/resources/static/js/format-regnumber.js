
function formatRegNumber(regNumber) {
    // Patterns: AA12 AAA, A123 AAA, AAA 123A, 1234 AAA
    const regexPatterns = [
        { regex: /^([A-Z]{2})(\d{2})([A-Z]{3})$/, format: '$1 $2 $3' }, // AA12 AAA
        { regex: /^([A-Z]{1})(\d{1,3})([A-Z]{3})$/, format: '$1$2 $3' }, // A123 AAA
        { regex: /^([A-Z]{3})(\d{1,3})([A-Z]{1})$/, format: '$1 $2$3' }, // AAA 123A
        { regex: /^(\d{1,4})([A-Z]{3})$/, format: '$1 $2' } // 1234 AAA
    ];

    // Check each pattern and format accordingly
    for (let pattern of regexPatterns) {
        if (regNumber.match(pattern.regex)) {
            return regNumber.replace(pattern.regex, pattern.format);
        }
    }

    // Return the original if no pattern matches (fallback)
    return regNumber;
}

export { formatRegNumber }; // Explicitly export the function
