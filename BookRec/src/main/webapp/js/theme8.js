// Function for dark theme
function activateDarkTheme() {
    document.documentElement.setAttribute('data-theme', 'dark');
    document.getElementById('theme').textContent = '☀️';
    localStorage.setItem('theme', 'dark'); // Store the theme
}

// Function for light theme
function activateLightTheme() {
    document.documentElement.removeAttribute('data-theme');
    document.getElementById('theme').textContent = '🌙';
    localStorage.setItem('theme', 'light'); // Store the theme
}

// Check if a theme was already set
function checkThemePreference() {
    const preferredTheme = localStorage.getItem('theme');
    if (preferredTheme === 'dark') {
        activateDarkTheme();
    } else {
        activateLightTheme();
    }
}

// Execute checkThemePreference at start
checkThemePreference();

// Add listener to the button
const themeToggleButton = document.getElementById('theme');

themeToggleButton.addEventListener('click', () => {
    const currentTheme = document.documentElement.getAttribute('data-theme');
    if (currentTheme === 'dark') {
        activateLightTheme();
    } else {
        activateDarkTheme();
    }
});

