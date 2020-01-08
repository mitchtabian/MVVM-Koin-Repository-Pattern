# Experimental TODO app
This app is for learning purposes. I'll be using it for experimentation. 

## Features:
1. MVVM
2. Repository Pattern
3. Koin
4. Room
5. Retrofit2 (adding later)

## TODO:
1. play around with ViewModelScope
    - Add delays and see if jobs are canceled 
2. Add network requests 
    - I want to see how difficult it is to make sequential requests. EX:   
        1. Retrieve result from network
        2. Retrieve result from cache
        3. Combine the two and emit a single result to UI
