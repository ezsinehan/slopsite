#!/bin/bash

echo "📘 Creating teacher..."
curl -s -X POST http://localhost:8080/api/teachers \
  -H "Content-Type: application/json" \
  -d '{"username":"prof.miller","password":"abc123","name":"Dr. Miller"}'

echo -e "\n👩‍🎓 Creating students..."
curl -s -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"username":"ava123","password":"pass","name":"Ava Johnson"}'

curl -s -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"username":"liam456","password":"pass","name":"Liam Chen"}'

curl -s -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -d '{"username":"zoe789","password":"pass","name":"Zoe Patel"}'

echo -e "\n📚 Creating course with capacity of 2..."
curl -s -X POST http://localhost:8080/api/courses \
  -H "Content-Type: application/json" \
  -d '{"name":"Introduction to AI", "time":"TR 3:00–4:15PM", "totalCapacity":2, "currentCapacity":0, "teacher":{"id":1}}'

echo -e "\n✅ Enrolling Ava..."
curl -s -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"student":{"id":1},"course":{"id":1}}'

echo -e "\n✅ Enrolling Liam..."
curl -s -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"student":{"id":2},"course":{"id":1}}'

echo -e "\n🚫 Attempting to enroll Zoe (should fail due to full class)..."
curl -s -X POST http://localhost:8080/api/enrollments \
  -H "Content-Type: application/json" \
  -d '{"student":{"id":3},"course":{"id":1}}'

echo -e "\n📋 Simulation complete."
