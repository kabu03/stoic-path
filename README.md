# Stoic Path

**Stoic Path** is an Android app built with Kotlin and developed using Android Studio. It introduces users to the ancient philosophy of Stoicism, a system of thought that originated in Ancient Greece and gained prominence in Ancient Rome. The app provides a modern, user-friendly experience for exploring the wisdom of Stoic philosophers, their teachings, and inspirational quotes. Stoic Path is designed for philosophy enthusiasts, mindfulness practitioners, and anyone interested in applying Stoic principles to improve their daily lives.

## Features

- **Daily Quote**  
  - Each day, the app fetches a new quote from a Stoic philosopher via an API request, refreshing daily at a set time.
  - The quote, along with the date, is saved in SharedPreferences, ensuring that users see the same quote for the entire day.

- **Philosophers Section**  
  - Discover and explore the lives of six prominent Stoic philosophers. Each profile includes the philosopher's icon, name, and a short, scrollable biography.
  - Users can favorite philosophers, making it easy to remember and return to their preferred figures.
  - Favorited philosophers are saved in SharedPreferences, allowing the app to remember these selections even after it’s closed.

- **Detailed Philosopher Profiles**  
  - Each philosopher has a dedicated page that provides an overview of their ideas, historical context, and influence on Stoic philosophy.

- **Dark Mode Support**  
  - Users can toggle between light and dark modes for a more comfortable reading experience, especially at night.
  - The app remembers the user’s mode preference, ensuring the selected theme persists on the next launch.

- **Daily Motivation Notification**  
  - A daily notification is sent at 9 AM Budapest time to inspire and motivate users with Stoic wisdom.
  - This feature is implemented using a Broadcast Receiver and a background Service to ensure timely delivery.

- **Persistent Data with SharedPreferences**  
  - The app makes extensive use of SharedPreferences to remember the daily quote, favorited philosophers, and the last selected theme (light or dark).
  - This persistence enhances the user experience by making the app feel seamless and responsive to individual preferences.
 
  ---
  ## Screenshots
  The following three screenshots show the _Philosophers Fragment_, _Detailed Philosopher Fragment for Marcus Aurelius_, and the _Daily Quote Fragment_ respectively.
  
![philosophersFragment](https://github.com/user-attachments/assets/827be0a2-2c3e-42b3-a082-6304dcb51154)
![detailedPhilosopherFragment](https://github.com/user-attachments/assets/a3d10585-12e5-4087-9aee-1701c6c8d850)
![dailyQuoteFragment](https://github.com/user-attachments/assets/470fc785-aa0f-4bcf-802f-0624c3b30b1f)
